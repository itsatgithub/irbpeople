package com.justinmind.MailSystem;

import java.util.List;
import java.util.ResourceBundle;

import utils.Pair;
import utils.filter.ListConfigurator;
import utils.hibernate.HibernateUtil;
import bussineslogic.controlers.UseCase;
import bussineslogic.objects.Personal;

public class StandaloneMailSender {

    private static ResourceBundle MAINCONFIG = ResourceBundle.getBundle("MainConfiguration");
    
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        String destinatarios = MAINCONFIG.getString("rrhhRecipients");
        
        Pair<Integer, List<Personal>> listContracts = UseCase.ObtainPersonalWithContractAboutToExpire(null, new ListConfigurator());
        
        Pair<Integer, List<Personal>> listGrants = UseCase.ObtainPersonalWithGrantAboutToExpire(null, new ListConfigurator());
        
        if(listContracts.getFirst() > 0 || listGrants.getFirst() > 0)
        {
            MailController.singleton().PrepareMail("ContractsAboutToExpire",
                new MailContractsAboutToExpire.MailData(destinatarios, listContracts.getSecond(), listGrants.getSecond(), "")    
                , HibernateUtil.getSession());
        }

    }

}
