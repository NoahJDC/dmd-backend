package com.dm_daddy.first_edition.Service;

import com.dm_daddy.first_edition.Model.Feats;
import com.dm_daddy.first_edition.Model.Items;
import com.dm_daddy.first_edition.Repositories.FeatsRepository;
import com.dm_daddy.first_edition.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTestService {

    @Autowired
    private ItemRepository itemRepo;

    public Items createItem(Items items) {
        return itemRepo.save(items);
    }

    public Iterable<Items> getAllItems() {
        return itemRepo.findAll();
    }


}
