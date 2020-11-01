package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.controllers.NotFoundException;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.repo.PictureRepository;


public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository pictureRepository) {
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
                .filter(pic -> pic.getPictureData().getData() != null)
                .map(pic -> pic.getPictureData().getData())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

}
