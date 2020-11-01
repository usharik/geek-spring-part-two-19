package ru.geekbrains.service;

import ru.geekbrains.persist.model.PictureData;


public interface PictureService {

    String getPictureContentTypeById(long id);

    byte[] getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);
}
