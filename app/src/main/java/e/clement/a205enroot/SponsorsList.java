package e.clement.a205enroot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Clément on 02/04/2018.
 */



public class SponsorsList {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("possesseur")
    @Expose
    private String possesseur;
    @SerializedName("console")
    @Expose
    private String console;
    @SerializedName("prix")
    @Expose
    private String prix;
    @SerializedName("nbre_joueurs_max")
    @Expose
    private String nbreJoueursMax;
    @SerializedName("commentaires")
    @Expose
    private String commentaires;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(String possesseur) {
        this.possesseur = possesseur;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNbreJoueursMax() {
        return nbreJoueursMax;
    }

    public void setNbreJoueursMax(String nbreJoueursMax) {
        this.nbreJoueursMax = nbreJoueursMax;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

}