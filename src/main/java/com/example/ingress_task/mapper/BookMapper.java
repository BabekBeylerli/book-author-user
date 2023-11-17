package com.example.ingress_task.mapper;

import com.example.ingress_task.dao.entity.BookEntity;
import com.example.ingress_task.dao.entity.UserEntity;
import com.example.ingress_task.model.BookDto;
import com.example.ingress_task.model.BookGetDto;
import com.example.ingress_task.model.BookLightDto;
import com.example.ingress_task.model.BookLightSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper mapper = Mappers.getMapper(BookMapper.class);

    BookDto mapEntityToDto(BookEntity bookEntity);

    BookLightDto mapEntityToLightDto(BookEntity bookEntity);

    BookEntity mapDtoToEntity(BookDto bookDto);

    @Mapping(target = "users", source = "authorId", qualifiedByName = "mapAuthorIdsToUsers")
    BookEntity mapDtoToLightSaveEntity(BookLightSaveDto bookDto);

    @Named("mapAuthorIdsToUsers")
    default List<UserEntity> mapAuthorIdsToUsers(List<Integer> authorId) {
        if (authorId == null || authorId.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserEntity> users = new ArrayList<>();
        for (Integer id : authorId) {
            UserEntity user = new UserEntity();
            user.setId(id);
            users.add(user);
        }
        return users;
    }


    BookEntity mapDtoToEntity(BookDto bookDto, Integer bookId);

    List<BookDto> mapEntityToDtos(List<BookEntity> bookEntities);
    List<BookLightDto> mapEntityToLightDtos(List<BookEntity> bookEntity);
    List<BookGetDto> mapEntityToGetDtos2(List<BookEntity> bookEntity);

}
