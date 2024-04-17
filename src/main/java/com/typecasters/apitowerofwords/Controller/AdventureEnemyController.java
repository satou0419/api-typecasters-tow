package com.typecasters.apitowerofwords.Controller;

import java.util.List;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import com.typecasters.apitowerofwords.Service.AdventureEnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/A-enemy")
public class AdventureEnemyController {
    @Autowired
    AdventureEnemyService aeserv;

    //C
    @PostMapping("/insertEnemy")
    public AdventureEnemyEntity insertEnemy(@RequestBody AdventureEnemyEntity enemy) {
        return aeserv.insertAdventureEnemy(enemy);
    }

    //R
    @GetMapping("/getAllEnemy")
    public List<AdventureEnemyEntity> getAllEnemy(){
        return aeserv.getAllAdventureEnemy();
    }

    //U
    @PutMapping("/updateEnemy")
    public AdventureEnemyEntity updateEnemy(@RequestParam int enemyId, @RequestBody AdventureEnemyEntity newEnemyDetails) {
        return aeserv.updateAdventureEnemy(enemyId, newEnemyDetails);
    }

    //D
    @DeleteMapping("deleteEnemy/{enemyId}")
    public String deleteEnemy(@PathVariable int enemyId) {
        return aeserv.deleteEnemy(enemyId);
    }


}