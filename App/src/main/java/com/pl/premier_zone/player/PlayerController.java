package com.pl.premier_zone.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer gamesPlayed,
            @RequestParam(required = false) Integer goals){
        if(teamName != null){
            return playerService.getPlayersFromTeam(teamName);
        }
        else if (name != null){
            return playerService.getPlayersByName(name);
        }
        else if (gamesPlayed != null){
            return playerService.getPlayersByPosition(gamesPlayed);
        }
        else if (goals != null){
            return playerService.getPlayersByTotalGoals(goals);
        }
        else return playerService.getPlayers();
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.addPLayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player updatedPlayer){
        Player updatedPlayerResponse = playerService.updatePlayer(updatedPlayer);
        if(updatedPlayerResponse != null){
            return new ResponseEntity<>(updatedPlayerResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}

