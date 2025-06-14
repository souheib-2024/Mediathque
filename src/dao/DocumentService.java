package dao;

import om.Document;
import om.Livre;
import om.Cassette;
import om.Periodique;

public class DocumentService {
   
    
    public static void sauvegarder(Document doc) {
        if (doc instanceof Livre) {
            LivreDAO.ajouterLivre((Livre) doc);
        } else if (doc instanceof Cassette) {
            CassetteDAO.ajouterCassette((Cassette) doc);
        } /** 
        else if (doc instanceof Periodique) {
            PeriodiqueDAO.ajouterPeriodique((Periodique) doc);
        }*/
    }
}