package com.codean.topup.mappers;

import com.codean.topup.models.dtos.GameDto;
import com.codean.topup.models.dtos.resultmodel.GameDtoList;
import com.codean.topup.models.entity.Game;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameMapper {

    List<GameDto> getAllByPage(Map<String, Object> params);
    Long countGames();

    List<GameDtoList> getDetailGameList();
    Game searchByName(String name);


    List<Game> getAll();
    Game getById(Long id);

    void create(Game game);
    void update(Game game);
    void delete(Long id);
}
