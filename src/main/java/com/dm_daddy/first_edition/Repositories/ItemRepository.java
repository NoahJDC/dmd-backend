package com.dm_daddy.first_edition.Repositories;

import com.dm_daddy.first_edition.Model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ItemRepository extends CrudRepository<Items, Long> {

    @Query("Select i from Items i")
    public Page<Items> getAllItems(Pageable pageable);

//    public Items getAllByName(String name);
//
//    public Items getAllByAttunmentId(Long id);




}
