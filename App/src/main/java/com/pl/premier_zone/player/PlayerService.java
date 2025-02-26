package com.pl.premier_zone.player;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String name) {
        return playerRepository.findAll().stream().
                filter(player -> player.getName().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(Integer gamesPlayed) {
        return playerRepository.findAll().stream()
                .filter(player -> gamesPlayed.equals(player.getGamesPlayed()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTotalGoals(Integer goals){
        return playerRepository.findAll().stream()
                .filter(player ->  goals.equals(player.getGoals()))
                .collect(Collectors.toList());
    }

    public Player addPLayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName());
        if(existingPlayer.isPresent()){
            Player currentPlater = existingPlayer.get();
            currentPlater.setName(updatedPlayer.getName());
            currentPlater.setTeam(updatedPlayer.getTeam());
            currentPlater.setGamesPlayed(updatedPlayer.getGamesPlayed());
            currentPlater.setGoals(updatedPlayer.getGoals());

            playerRepository.save(currentPlater);
            return currentPlater;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String name){
        playerRepository.deleteByName(name);
    }

}
