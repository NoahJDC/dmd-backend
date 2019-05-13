package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.Feats;
import com.dm_daddy.first_edition.Model.Items;
import com.dm_daddy.first_edition.Model.RefCode;
import com.dm_daddy.first_edition.Repositories.ItemRepository;
import com.dm_daddy.first_edition.Repositories.RefCodeRepository;
import com.dm_daddy.first_edition.Service.ItemTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RepositoryRestController
@CrossOrigin
@RequestMapping(value="/items")
public class ItemController {
    @Autowired
    private ItemRepository repo;

    @Autowired
    private RefCodeRepository refRepo;

    @Autowired
    private ItemTestService itemTestService;


    //--- Load Item List -----
    //------------------------
    @GetMapping(value ="/list", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Items> getAllItemList(){
        return (List<Items>) itemTestService.getAllItems();
    }


    //---- Load Item Type -------
    //---------------------------
    @GetMapping("/type")
    public List<RefCode>  getItemType(){
        return refRepo.findByParentId((long) 9);
    }

    //----- Load Rarity ---------
    //---------------------------
    @GetMapping("/rarity")
    public List<RefCode> getRarity(){
        return refRepo.findByParentId((long) 1);
    }

    //---- Load Attunement -----
    //--------------------------
    @GetMapping("/attunement")
    public List<RefCode> getAttunment(){
        return refRepo.findByParentId((long) 91);

    }

    //---- Load Armor Type ------
    //---------------------------
    @GetMapping("/armorType")
    public List<RefCode> getArmorType(){
        return refRepo.findByParentId((long) 19);
    }

    //----- Load Weapon Type ---
    //--------------------------
    @GetMapping("/weaponType")
    public List<RefCode> getWeaponType(){
        return refRepo.findByParentId((long) 34);
    }

    //---- Create an Item -------
    //---------------------------
    @RequestMapping(value="/create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    @PostMapping
    public Items createItem(@RequestBody Items item){
//        Items createdItem = repo.save(item);
        return itemTestService.createItem(item);
    }

    //---- Delete an Item -----
    //-------------------------
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<Items> deleteItem(@PathVariable Long id){
        repo.deleteById(id);
        return (List<Items>) repo.findAll();
    }

}
