/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Epulapp
 */
public class Message {
    private String titre;
    private String date;
    private int idAuteur;
    private String identiteAuteur;
    
    public Message(){
        
    }
    
    public Message(String titre, String date, int idAuteur, String identiteAuteur){
        this.titre = titre;
        this.date = date;
        this.idAuteur = idAuteur;
        this.identiteAuteur = identiteAuteur;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the idAuteur
     */
    public int getIdAuteur() {
        return idAuteur;
    }

    /**
     * @param idAuteur the idAuteur to set
     */
    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    /**
     * @return the identiteAuteur
     */
    public String getIdentiteAuteur() {
        return identiteAuteur;
    }

    /**
     * @param identiteAuteur the identiteAuteur to set
     */
    public void setIdentiteAuteur(String identiteAuteur) {
        this.identiteAuteur = identiteAuteur;
    }
}
