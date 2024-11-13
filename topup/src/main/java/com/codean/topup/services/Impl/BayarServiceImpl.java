package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.mappers.BayarMapper;
import com.codean.topup.mappers.MethodBayarMapper;
import com.codean.topup.mappers.PaketTopupMapper;
import com.codean.topup.models.dtos.BayarDto;
import com.codean.topup.models.entity.Bayar;
import com.codean.topup.models.entity.MethodBayar;
import com.codean.topup.models.entity.PaketTopup;
import com.codean.topup.services.BayarService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BayarServiceImpl implements BayarService {

    private static final Logger log = LoggerFactory.getLogger(BayarServiceImpl.class);
    @Autowired
    BayarMapper bayarMapper;

    @Autowired
    PaketTopupMapper paketTopupMapper;

    @Autowired
    MethodBayarMapper methodBayarMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void insert(BayarDto bayarDto) throws ResourceAlreadyException {

        PaketTopup paketTopup = paketTopupMapper.getById(bayarDto.getIdPaket());

        if (paketTopup == null){
            String message = "id paket not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        MethodBayar methodBayar = methodBayarMapper.getById(bayarDto.getIdMethod());
        if (methodBayar == null){
            String message = "id method not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        if (paketTopup.getIdPaket().equals(bayarDto.getIdPaket()) &&
            methodBayar.getIdMethod().equals(bayarDto.getIdMethod())) {
            String message = "resource already exists";
            log.info(message);
            throw new ResourceAlreadyException(message);
        }

        Bayar bayar = new Bayar();
        bayar.setIdPaket(bayarDto.getIdPaket());
        bayar.setIdMethod(bayarDto.getIdMethod());

        bayarMapper.insert(bayar);

    }

    @Override
    public BayarDto getById(Long id) {
        Bayar bayar = bayarMapper.getById(id);

        if (bayar == null){
            String message = "id bayar not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        return toDto(bayar);

    }

    @Override
    public void delete(Long id) {
        Bayar bayar = bayarMapper.getById(id);

        if (bayar == null){
            String message = "id bayar not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        bayarMapper.delete(id);
    }

    private BayarDto toDto(Bayar bayar){
        return modelMapper.map(bayar, BayarDto.class);
    }

    private Bayar toEntity(BayarDto bayarDto){
        return modelMapper.map(bayarDto, Bayar.class);
    }
}
