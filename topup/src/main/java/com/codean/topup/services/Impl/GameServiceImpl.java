package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.GameMapper;
import com.codean.topup.models.dtos.GameDto;
import com.codean.topup.models.dtos.resultmodel.GameDtoList;
import com.codean.topup.models.entity.Game;
import com.codean.topup.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameMapper gameMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<GameDtoList> gameResultListMap() throws ResourceNotExistException {
        List<GameDtoList> gameDtoLists = gameMapper.getDetailGameList();

        return gameDtoLists;
    }

    @Override
    public Game searchByName(String name) throws ResourceNotExistException {

        return gameMapper.searchByName(name);
    }

    @Override
    public List<GameDto> getAll() throws ResourceNotExistException {
        List<Game> gameDtoList = gameMapper.getAll();

        if (gameDtoList == null){
            throw new ResourceNotExistException("list is empty");
        }

        return gameDtoList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public GameDto getById(Long id) throws ResourceNotExistException {
        Game game = gameMapper.getById(id);

        if (game == null){
            throw new ResourceNotExistException("that game with id " + id + " not found");
        }

        return toDto(game);
    }

    @Override
    public void create(GameDto gameDto) throws ResourceAlreadyException {

        Game game1 = gameMapper.searchByName(gameDto.getNamaGame());
        if (game1 != null){
            throw new ResourceAlreadyException("resource with that name already exist");
        }

        Game game = new Game();
        game.setNamaGame(gameDto.getNamaGame());
        game.setJenisGame(gameDto.getJenisGame());

        gameMapper.create(game);
    }

    @Override
    public void update(Long id, GameDto gameDto) throws ResourceNotExistException, ResourceAlreadyException {
        Game checkExist = gameMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that game with id " + id + " not found");
        }

        if (checkExist.getNamaGame().equals(gameDto.getNamaGame())){
            throw new ResourceAlreadyException("that game with name " + gameDto.getNamaGame() + " already exist");
        }

        checkExist.setNamaGame(gameDto.getNamaGame());
        checkExist.setJenisGame(gameDto.getJenisGame());

        gameMapper.update(checkExist);
    }

    @Override
    public void delete(Long id) throws ResourceNotExistException {
        Game checkExist = gameMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that id with " + id + " not found");
        }

        gameMapper.delete(id);
    }

    private GameDto toDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }

    private Game toEntity(GameDto gameDto){
        return modelMapper.map(gameDto, Game.class);
    }
}
