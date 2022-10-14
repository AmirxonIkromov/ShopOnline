package uz.pdp.internetmagazin.service;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.internetmagazin.exception.InputDataExistsException;
import uz.pdp.internetmagazin.payload.AddResult;

import java.io.IOException;

public interface PhotoService {
    AddResult add(MultipartFile request, MultipartHttpServletRequest  multipartHttpServletRequest) throws InputDataExistsException, IOException;

    AddResult edit(MultipartFile request, MultipartHttpServletRequest  multipartHttpServletRequest,Integer photo_id) throws InputDataExistsException, IOException;
}
