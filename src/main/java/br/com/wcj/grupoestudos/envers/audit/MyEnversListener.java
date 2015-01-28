package br.com.wcj.grupoestudos.envers.audit;

import org.apache.log4j.Logger;
import org.hibernate.envers.RevisionListener;

public class MyEnversListener implements RevisionListener {

    private static final Logger LOGGER = Logger
            .getLogger(MyEnversListener.class);

    @Override
    public void newRevision(Object revisionEntity) {

        try {
            MyRevisionEntity myRevEntity = (MyRevisionEntity) revisionEntity;

//            String usuarioLogado = ((HttpSession) FacesContext
//                    .getCurrentInstance().getExternalContext().getSession(true))
//                    .getAttribute("usuarioLogado").toString();
            
            String usuarioLogado = "31869886836";
            myRevEntity.setLogin(usuarioLogado);

        } catch (Exception e) {
            LOGGER.error("###### Revisar o HIBERNATE ENVERS", e);
        }

    }

}
