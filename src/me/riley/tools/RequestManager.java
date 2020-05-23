package me.riley.tools;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class RequestManager {

    private HashMap<Player, Player> requests;
    public HashMap<Player, Player> messages;

    public RequestManager() {
        requests = new HashMap<>();
        messages = new HashMap<>();
    }

    public void addRequest(Player p, Player p2) {
        requests.put(p, p2);
    }

    public void replaceRequest(Player p, Player p2) {
        requests.replace(p, p2);
    }

    public void removeRequest(Player p) {
        requests.remove(p);
    }

    public boolean containsRequest(Player p) {
        return requests.containsKey(p);
    }

    public Player getRequest(Player p) {
        return requests.get(p);
    }
}
