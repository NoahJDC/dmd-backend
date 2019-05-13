package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.HomebrewItems;
import com.dm_daddy.first_edition.Model.Items;
import com.dm_daddy.first_edition.Model.RefCode;
import com.dm_daddy.first_edition.Model.User;
import com.dm_daddy.first_edition.Repositories.HomebrewItemRepository;
import com.dm_daddy.first_edition.Repositories.RefCodeRepository;
import com.dm_daddy.first_edition.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RepositoryRestController
@CrossOrigin
public class HomebrewItemController {

    @Autowired
    private HomebrewItemRepository repo;

    @Autowired
    private RefCodeRepository refRepo;

    @Autowired
    private UserRepository userRepository;


    //----- Load All Items --------
    //-----------------------------
    @GetMapping("/homebrewItems/all")
    public Page<HomebrewItems> getAllItems(@RequestParam int page, @RequestParam int size){
//        Page<HomebrewItems> homebrewItemsList = (Page<HomebrewItems>) repo.getAllHomebrewItems(PageRequest.of(page, size));
//        return homebrewItemsList;
        return repo.getAllHomebrewItems(PageRequest.of(page, size));
    }

    //---- Load Hb Item List ----
    //---------------------------
    @GetMapping("/homebrewItems/list")
    public List<HomebrewItems> getAllHbItems(){
        return  (List<HomebrewItems>) repo.findAll();
    }

    //---- Load Item Type -------
    //---------------------------
    @GetMapping("/homebrewItems/type")
    public List<RefCode> getItemType(){
        return refRepo.findByParentId((long) 9);
    }

    //----- Load Rarity ---------
    //---------------------------
    @GetMapping("/homebrewItems/rarity")
    public List<RefCode> getRarity(){
        return refRepo.findByParentId((long)1);
    }

    //---- Load Attunement -----
    //--------------------------
    @GetMapping("/homebrewItems/attunement")
    public List<RefCode> getAttunment(){
        return refRepo.findByParentId((long) 91);
    }

    //---- Load Armor Type ------
    //---------------------------
    @GetMapping("/homebrewItems/armorType")
    public List<RefCode> getArmorType(){
        return refRepo.findByParentId((long) 19);
    }

    //----- Load Weapon Type ---
    //--------------------------
    @GetMapping("/homebrewItems/weaponType")
    public List<RefCode> getWeaponType(){
        return refRepo.findByParentId((long) 34);
    }

    //---- Create an Item -------
    //---------------------------
    @RequestMapping(value="/homebrewItems/create")
    @PostMapping
    public HomebrewItems createItem(@RequestBody HomebrewItems homebrewItem){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);
//        homebrewItem.setCreator(currentPrincipalName);
        return repo.save(homebrewItem);

    }

    //---- Delete an Item -----
    //-------------------------
    @RequestMapping(value="/homebrewItems/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<HomebrewItems> deleteItem(@PathVariable Long id){
        repo.deleteById(id);
        return (List<HomebrewItems>) repo.findAll();
    }

}
