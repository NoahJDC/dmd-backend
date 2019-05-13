package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.PlayerCampaigns;
import com.dm_daddy.first_edition.Model.PlayerCharacter;
import com.dm_daddy.first_edition.Repositories.PendingCampaignInterface;
import com.dm_daddy.first_edition.Repositories.PlayerCampaignRepository;
import com.dm_daddy.first_edition.Repositories.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RepositoryRestController
@CrossOrigin
public class PlayerCampaignController {
    @Autowired
    private PlayerCampaignRepository repo;

    @Autowired
    private PendingCampaignInterface pendRepo;

    @Autowired
    private PlayerCharacterRepository charRepo;

    //------ Load Campaign by Player ----
    //-------------------------------------
    @GetMapping("campaign/{player}/{dm}")
    public List<PlayerCampaigns> getCampaignByPlayer(@PathVariable String player, @PathVariable String dm){
        return repo.findPlayerCampaignsByPlayerCharacterCreatorIdUsernameOrCreatorIdUsername(player,dm);
    }

    //------Load Campaign By Id -----
    //-------------------------------
    @GetMapping("campaign/{id}")
    public List<PlayerCampaigns> getCampaignById(@PathVariable Long id){
        return repo.findPlayerCampaignsByCampaignIdAndPlayerCharacterIsNotNull(id);
    }

    //--- Add Character to Campaign ----
    //----------------------------------
    @PostMapping("campaign/add/{id}")
    public PlayerCampaigns addCharacter(@RequestBody PlayerCampaigns playerCampaigns, @PathVariable Long id){
        pendRepo.deleteById(id);
        Optional<PlayerCharacter> pc = charRepo.findById(playerCampaigns.getPlayerCharacter().getId());
        pc.get().setCampId(playerCampaigns.getCampaign());
        charRepo.save(pc.get());
        PlayerCampaigns addCharacter = repo.save(playerCampaigns);
        return addCharacter;
    }

    //--- Delete Campaign ------
    //-------------------------
    @RequestMapping(value = "/playerCampaign/delete/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<PlayerCampaigns> deleteCampaign(@PathVariable Long id){
        repo.deleteById(id);
        return (List<PlayerCampaigns>) repo.findAll();
    }

    //--- Leave/Remove Campaign ---
    //----------------------
    @RequestMapping(value = "/campaign/leave/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<PlayerCampaigns> leaveCampaign(@PathVariable Long id){
        repo.deletePlayerCampaignsByPlayerCharacterId(id);
        Optional<PlayerCharacter> playerCharacter = charRepo.findById(id);
        playerCharacter.get().setCampId(null);
        return (List<PlayerCampaigns>) repo.findAll();
    }





}
