/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import Utilitaires.Utilitaire;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pkgConso.MdbAcquisition;

/**
 *
 * @author Epulapp
 */
public class Auteur {
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/NetArticlesRest/webresources";
    Message m = null;
    
    private Integer idAuteur;
    private String identiteAuteur;
    private String adresseAuteur;
    private String loginAuteur;
    private String pwdAuteur;
    private List<Redige> redigeList;
    private List<Droits> droitsList;
    
    
    public Auteur(){
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("webservice");
    }
    
    public <T> T connecterAuteur(Class<T> responseType, String login) throws ClientErrorException, Exception {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getConnexionAuteur/{0}", new Object[]{login}));
        Response r = resource.request(MediaType.APPLICATION_JSON).get();
        if (r.getStatus() != Response.Status.OK.getStatusCode()){
            JsonObject jsonObject = Utilitaire.convertJson(r.readEntity(String.class));
            String message = jsonObject.getString("message");
            throw new Exception(message);
        }
        T cli = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        return cli;
    }
    
    public List<Message> getListeAcquisitions(int idAuteur) throws Exception{
        List<Message> liste = new ArrayList<Message>();
                
        MdbAcquisition acquisition = new MdbAcquisition();
        File f = new File("AcquisitionBL.txt");
        if(f.exists()){
            String line = null;
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                int i = 1; //initialisation du numero de ligne
                int idA = -1;
                while ((line = br.readLine()) != null) {
                        if (line.indexOf("Titre") != -1){
                            m = new Message();
                            idA = -1;
                            m.setTitre(line.replace("Titre : ", ""));
                            System.out.println(line.replace("Titre : ", ""));
                        }
                        if (line.indexOf("Date") != -1){
                            m.setDate(line.replace("Date : ", ""));
                            System.out.println(line.replace("Date : ", ""));
                        }
                        if (line.indexOf("IdAuteur") != -1){
                            idA = Integer.parseInt(line.replace("IdAuteur : ", ""));
                            m.setIdAuteur(idA);
                            System.out.println(line.replace("IdAuteur : ", ""));
                        }
                        if (line.indexOf("IdentiteAuteur") != -1){
                            m.setIdentiteAuteur(line.replace("IdentiteAuteur : ", ""));
                            System.out.println(line.replace("IdentiteAuteur : ", ""));
                            if(idA == idAuteur){
                                liste.add(m);
                            }
                        }
                    i++;
                }
                br.close();
            }
            catch(FileNotFoundException exc) {
                System.out.println("File not found" );
                throw exc;
            }
            catch(IOException ioe) {
                System.out.println("Erreur IO" );
                throw ioe;
            }
        }
        
        return liste;
    }

    /**
     * @return the idAuteur
     */
    public Integer getIdAuteur() {
        return idAuteur;
    }

    /**
     * @param idAuteur the idAuteur to set
     */
    public void setIdAuteur(Integer idAuteur) {
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

    /**
     * @return the adresseAuteur
     */
    public String getAdresseAuteur() {
        return adresseAuteur;
    }

    /**
     * @param adresseAuteur the adresseAuteur to set
     */
    public void setAdresseAuteur(String adresseAuteur) {
        this.adresseAuteur = adresseAuteur;
    }

    /**
     * @return the loginAuteur
     */
    public String getLoginAuteur() {
        return loginAuteur;
    }

    /**
     * @param loginAuteur the loginAuteur to set
     */
    public void setLoginAuteur(String loginAuteur) {
        this.loginAuteur = loginAuteur;
    }

    /**
     * @return the pwdAuteur
     */
    public String getPwdAuteur() {
        return pwdAuteur;
    }

    /**
     * @param pwdAuteur the pwdAuteur to set
     */
    public void setPwdAuteur(String pwdAuteur) {
        this.pwdAuteur = pwdAuteur;
    }

    /**
     * @return the redigeList
     */
    public List<Redige> getRedigeList() {
        return redigeList;
    }

    /**
     * @param redigeList the redigeList to set
     */
    public void setRedigeList(List<Redige> redigeList) {
        this.redigeList = redigeList;
    }

    /**
     * @return the droitsList
     */
    public List<Droits> getDroitsList() {
        return droitsList;
    }

    /**
     * @param droitsList the droitsList to set
     */
    public void setDroitsList(List<Droits> droitsList) {
        this.droitsList = droitsList;
    }
}
