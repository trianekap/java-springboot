package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.GameDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.dtos.resultmodel.GameDtoList;
import com.codean.topup.models.entity.Game;

import java.util.List;

public interface GameService {
    PageResult<GameDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException;

    List<GameDtoList> gameResultListMap() throws ResourceNotExistException;

    Game searchByName(String name) throws ResourceNotExistException;

    List<GameDto> getAll() throws ResourceNotExistException;
    GameDto getById(Long id) throws ResourceNotExistException;

    void create(GameDto gameDto) throws ResourceAlreadyException;
    void update(Long id, GameDto gameDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete(Long id) throws ResourceNotExistException;
}
