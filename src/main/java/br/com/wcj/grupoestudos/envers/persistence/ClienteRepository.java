package br.com.wcj.grupoestudos.envers.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import br.com.wcj.grupoestudos.envers.modelo.Cliente;

public class ClienteRepository extends PersistenceRepository<Cliente, Long> {

    private static final Logger LOGGER = Logger
            .getLogger(ClienteRepository.class);

    public ClienteRepository() {
        super(Cliente.class);
    }

    public void buscaAudit(Long clinteID) {
        try {
            AuditReader reader = AuditReaderFactory.get(getEm());

            AuditQuery query = reader.createQuery().forRevisionsOfEntity(
                    Cliente.class, true, true);
            query.add(AuditEntity.id().eq(clinteID));

            List<Cliente> clientes = query.getResultList();
            for (Cliente cliente : clientes) {
                LOGGER.info("############ " + cliente.getRazaoSocial());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
