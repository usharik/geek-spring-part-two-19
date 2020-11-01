package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ru.geekbrains.controllers.NotFoundException;
import ru.geekbrains.controllers.ServerInternalException;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.repo.PictureRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


public class PictureServiceFileImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceFileImpl.class);

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository pictureRepository;

    public PictureServiceFileImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String getPictureContentTypeById(long id) {
        return pictureRepository.findById(id)
                .map(Picture::getContentType)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public byte[] getPictureDataById(long id) {
        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getFileName() != null)
                .map(pic -> Paths.get(storagePath, pic.getPictureData().getFileName()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        logger.error("Can't read picture file ", ex);
                        throw new ServerInternalException();
                    }
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Path.of(storagePath, fileName))) {
            outputStream.write(picture);
        } catch (IOException ex) {
            logger.error("Can't create picture file ", ex);
            throw new ServerInternalException();
        }

        return new PictureData(fileName);
    }
}
