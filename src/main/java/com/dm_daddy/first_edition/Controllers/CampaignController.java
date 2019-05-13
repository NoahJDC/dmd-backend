package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.Campaign;
import com.dm_daddy.first_edition.Model.PlayerCampaigns;
import com.dm_daddy.first_edition.Repositories.CampaignRepository;
import com.dm_daddy.first_edition.Repositories.PlayerCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;



@RestController
@RepositoryRestController
@CrossOrigin
public class CampaignController {
    @Autowired
    private CampaignRepository repo;

    @Autowired
    private PlayerCampaignRepository pcRepo;

    //---- Load All Campaigns -------
    //-------------------------------
   @GetMapping("/campaign/all")
    public List<Campaign> getAllCampaigns(){
        return (List<Campaign>) repo.findAll();
    }

    //---Load Campaign by Id -----
    //---------------------------
    @GetMapping("/campaign/single/{id}")
    public List<Campaign> getCampaignById(@PathVariable Long id){
        return repo.findCampaignById(id);
    }

    //--- Create Campaign ---
    //-----------------------
    @PostMapping("/campaign/create")
    public Campaign createCampaign(@RequestBody Campaign campaign){
        Calendar now = GregorianCalendar.getInstance();
        Timestamp createdDate = new Timestamp(now.getTimeInMillis());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        campaign.setPlayer(currentPrincipalName);
        campaign.setStartDate(createdDate);
        Campaign createdCampaign = repo.save(campaign);

        PlayerCampaigns playerCampaigns = new PlayerCampaigns();
        playerCampaigns.setCreatorId(campaign.getCreatorId());
//        playerCampaigns.setDungeonMaster(currentPrincipalName);
        playerCampaigns.setCampaign(campaign);
        pcRepo.save(playerCampaigns);

       return createdCampaign;
    }

    //--- Delete Campaign ----
    //------------------------
    @RequestMapping(value="/campaign/delete/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<Campaign> deleteItem(@PathVariable Long id){
        pcRepo.deletePlayerCampaignsByCampaignId(id);
        repo.deleteById(id);
        List<Campaign> campList = (List<Campaign>) repo.findAll();
        return campList;
    }

}
