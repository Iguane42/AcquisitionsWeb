/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgConso;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Epulapp
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MdbAcquisition")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Depeches")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "MdbAcquisition")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
//    ,
//        @ActivationConfigProperty(propertyName = "addresslist", propertyValue = "127.0.0.1:7676")
})
public class MdbAcquisition implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    static final Logger logger = Logger.getLogger("Acquisition");

    public MdbAcquisition() throws Exception {
        try {
            FileHandler fh = new FileHandler("AcquisitionBL.txt", false);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = null;
        String msg;
        try {
            if (message instanceof MapMessage) {
                mapMessage = (MapMessage) message;
                msg = "Titre : " + mapMessage.getString("titre") + "\n";
                msg += "Date : " + mapMessage.getString("date") + "\n";
                msg += "IdAuteur : " + mapMessage.getString("id_auteur") + "\n";
                msg += "IdentiteAuteur : " + mapMessage.getString("identite_auteur") + "\n";
                logger.log(Level.INFO, "\n{0}", msg);
            }
        } catch (Exception e) {
            mdc.setRollbackOnly();
            logger.log(Level.INFO, "Erreur sur réception message : {0}", e.getMessage());
        }
    }

}
