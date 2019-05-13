package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.Campaign;
import com.dm_daddy.first_edition.Model.PendingCampaign;
import com.dm_daddy.first_edition.Repositories.PendingCampaignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RepositoryRestController
@CrossOrigin
public class PendingCampaignController {
    @Autowired
    private PendingCampaignInterface repo;

    //--- Send Invite -----
    //--------------------
    @PostMapping("/invite")
    public PendingCampaign pendingCampaign(@RequestBody PendingCampaign pendingCampaign){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
        Calendar now = GregorianCalendar.getInstance();
        Timestamp createdDate = new Timestamp(now.getTimeInMillis());
//        pendingCampaign.setDungeonMaster(currentPrincipalName);
        pendingCampaign.setRequestDate(createdDate);
        repo.save(pendingCampaign);
        return pendingCampaign;
    }

    //---Load Pending Request for Player ----
    //---------------------------------------
    @GetMapping("/invite/{username}")
    public List<PendingCampaign> getPendingCampaignByPlayer(@PathVariable String username){
        return repo.findPendingCampaignByPlayerContaining(username);
    }

    //--- Decline Campaign Invite----
    //-------------------------------
    @RequestMapping(value = "/invite/delete/{id}", method = RequestMethod.DELETE)
    public List<PendingCampaign> deleteInvite(@PathVariable Long id){
        repo.deleteById(id);
        return (List<PendingCampaign>) repo.findAll();
    }



}
