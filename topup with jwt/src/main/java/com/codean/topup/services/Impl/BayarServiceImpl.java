package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.*;
import com.codean.topup.models.dtos.BayarDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.entity.*;
import com.codean.topup.services.BayarService;
import com.codean.topup.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    UserMapper userMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SaldoMapper saldoMapper;

    @Override
    public PageResult<BayarDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException {
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNumber - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<BayarDto> bayarDtoList = bayarMapper.getAllByPage(params);

        Long totalElement = bayarMapper.countBayar();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage;
        isLastPage = pageNumber >= totalPage;

        PageResult<BayarDto> pageResult = new PageResult<>();
        pageResult.setContent(bayarDtoList);
        pageResult.setPage_number(pageNumber);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

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

        User user = userMapper.getId(bayarDto.getIdUser());
        if (user == null){
            String message = "id user not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        Bayar bayar = new Bayar();
        bayar.setIdPaket(bayarDto.getIdPaket());
        bayar.setIdMethod(bayarDto.getIdMethod());
        bayar.setIdUser(bayarDto.getIdUser());
        bayar.setTotalBayar(bayarDto.getTotalBayar());
        bayar.setIdSaldo(bayarDto.getIdSaldo());

        Saldo saldo = saldoMapper.getId(bayarDto.getIdSaldo());
        if (saldo == null) {
            String message = "id saldo not found";
            log.info(message);
            throw new RuntimeException(message);
        }

        if (!methodBayar.getNamaMethod().equals(saldo.getJenisSaldo())){
            throw new RuntimeException("method pembayaran dan saldo pembayaran tidak sesuai!");
        }

        long total = saldo.getJumlahSaldo() - (paketTopup.getHarga() +
                methodBayar.getBiayaTransaksi());

        if (total < 0){
            throw new RuntimeException("saldo tidak cukup!");
        }

        bayar.setTotalBayar(total);
        bayarMapper.insert(bayar);

        saldo.setJumlahSaldo(total);
        saldoMapper.update(saldo);

    }

    @Override
    public List<BayarDto> getAll() throws ResourceNotExistException {
        List<Bayar> bayarList = bayarMapper.getAll();

        if (bayarList == null){
            throw new ResourceNotExistException("list is empty");
        }

        return bayarList.stream().map(this::toDto).collect(Collectors.toList());
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
