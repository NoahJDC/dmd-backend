package com.dm_daddy.first_edition.Controllers;

import com.dm_daddy.first_edition.Model.*;
import com.dm_daddy.first_edition.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RepositoryRestController
@CrossOrigin
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository repo;

    @Autowired
    private RefCodeRepository refRepo;

    @Autowired
    private PlayerCampaignRepository campRepo;

    @Autowired
    private RaceRepository raceRepo;

    @Autowired
    private SkillBonusRepository skillRepo;

    @Autowired
    private BackgroundRepository bgRepo;

    @Autowired
    private RacialFeaturesRepository rfRepo;

    @Autowired
    private FeatsRepository featsRepo;

    //------ Load Characters By Creator ----
    //-------------------------------------
    @GetMapping("/profile/{player}/characters")
    public List<PlayerCharacter>  getCharacterByCreator(@PathVariable String player){
//    List<PlayerCharacter> characterList = repo.findPlayerCharacterByPlayerContaining(player);
    return repo.findPlayerCharacterByCreatorIdUsername(player);
    }

    //---- Load Character By Id ------
    //--------------------------------
    @GetMapping("/character/{id}")
    public List<PlayerCharacter> getCharacterById(@PathVariable Long id){
        return repo.findPlayerCharacterById(id);
    }

    //--- Load Character By Creator and Camp_Id is null ------
    //--------------------------------------------------------
    @GetMapping("/character/user/{player}")
    public List<PlayerCharacter> getChar(@PathVariable String player){
        return repo.findPlayerCharacterByCreatorIdUsernameContainingAndCampIdIsNull(player);
    }

    //----- Load alignment ------
    //---------------------------
    @GetMapping("/alignment")
    public List<RefCode> getAlignment(){
        return refRepo.findByParentId((long) 124);
    }

    //---- Load Background -----
    //--------------------------
    @GetMapping("/background")
    public List<Backgrounds> getBackground(){
        return (List<Backgrounds>) bgRepo.findAll();
    }


    //---- Load Races -----
    //---------------------
    @GetMapping("/races")
    public List<Race> getRaces(){
        return (List<Race>) raceRepo.findAll();
    }

    //--- Load Classes -----
    //----------------------
    @GetMapping("/classes")
    public List<RefCode> getClasses(){
        return refRepo.findByParentId((long) 104);
    }

    @GetMapping("/abilities")
    //-- Load Abilities ----
    //----------------------
    public List<RefCode> getAbilities(){
        return refRepo.findByParentId((long) 134);
    }
    @GetMapping("skills")
    //-- Load Skills -----
    //--------------------
    public List<RefCode> getSkills(){
        return refRepo.findByParentId((long) 141);
    }

    //--- Load Skill by Class Name ----
    //---------------------------------
    @GetMapping("/skills/{classname}")
    public List<RefCode> getSkillByName(@PathVariable String classname){
        return refRepo.findRefCodeByDescriptionContaining(classname);
    }

    //---- Create a character ----
    //----------------------------
    @RequestMapping(value="/character/create")
    @PostMapping
    @Transactional
    public PlayerCharacter createCharacter(@RequestBody PlayerCharacter playerCharacter){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        playerCharacter.setPlayer(currentPrincipalName);
        if(playerCharacter.getSkillBonus() != null){
            for(SkillBonus bonus: playerCharacter.getSkillBonus()) {
                bonus.setPlayerCharacter(playerCharacter);


            }
        }
        return repo.save(playerCharacter);
    }



    //---- Delete a character ---
    //---------------------------
    @RequestMapping(value = "/character/delete/{id}", method = RequestMethod.DELETE)
    @Transactional
    public List<PlayerCharacter> deleteCharacter(@PathVariable Long id){
        PlayerCampaigns pc = campRepo.findPlayerCampaignsByPlayerCharacterId(id);
        if(pc != null){
            campRepo.deleteById(pc.getId());
        }
        repo.deleteById(id);
        return (List<PlayerCharacter>) repo.findAll();
    }

    //---- Load Proficient Skills by Player Character Id ---------
    //------------------------------------------------------------
    @GetMapping("/character/proficiency-skills/{id}")
    public List<SkillBonus> getAllSkillProf(@PathVariable Long id){
        return skillRepo.findSkillBonusByPlayerCharacterId(id);
    }

    //---- Load all Proficiency by Creator Id ----
    //-----------------------------------------
    @GetMapping("/all-characters/{username}")
    public List<SkillBonus> getSkillByCreatorId(@PathVariable String username) {
        return skillRepo.findSkillBonusByPlayerCharacterCreatorIdUsername(username);
    }

    //---- Load all Proficiency by Campaign Id ---
    //--------------------------------------------
    @GetMapping("/campaign/character-skills/{id}")
    public List<SkillBonus> getSkillByCampId(@PathVariable Long id) {
        return skillRepo.findSkillBonusByPlayerCharacterCampIdId(id);
    }

    //---- Load Racial Features By Race Id -----
    //------------------------------------------
    @GetMapping("/race/feature/{id}")
    public List<RacialFeatures> getRaceFeatureByRaceId(@PathVariable Long id) {
        return rfRepo.findRacialFeaturesByRaceId(id);
    }

    //---- Load Racial Features -----
    //------------------------------
    @GetMapping("race/feature")
    public List<RacialFeatures> getRacialFeatures(){
        return (List<RacialFeatures>) rfRepo.findAll();
    }

    //---- Load Feats ------
    //----------------------
    @GetMapping("/feats")
    public List<Feats> getAllFeats() {
        return (List<Feats>) featsRepo.findAll();
    }
}


