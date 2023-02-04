package com.leanova.heroesofnova.modelos;

public class InvItem {
    private int mochilaId;
    private int personajeId;
    private int itemId;
    private int cantidad;
    private Item item;

    public InvItem() { }
    public InvItem(int mochilaId, int personajeId, int itemId, int cantidad, Item item) {
        this.mochilaId = mochilaId;
        this.personajeId = personajeId;
        this.itemId = itemId;
        this.cantidad = cantidad;
        this.item = item;
    }

    public int getMochilaId() {
        return mochilaId;
    }

    public void setMochilaId(int mochilaId) {
        this.mochilaId = mochilaId;
    }

    public int getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(int personajeId) {
        this.personajeId = personajeId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
