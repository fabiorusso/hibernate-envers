package br.com.wcj.grupoestudos.envers.persistence;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import br.com.wcj.grupoestudos.envers.modelo.Fornecedor;

public class FornecedorRepository extends
		PersistenceRepository<Fornecedor, Long> {

	public FornecedorRepository() {
		this(Fornecedor.class);
	}

	private FornecedorRepository(Class<Fornecedor> clazz) {
		super(clazz);
	}

	public List<Fornecedor> auditBuscaPorTipoPessoa(String tipoPessoa) {
		try {
			AuditReader reader = AuditReaderFactory.get(getEm());

			AuditQuery query = reader.createQuery().forRevisionsOfEntity(
					Fornecedor.class, true, true);

			query.add(AuditEntity.property("tipoPessoa").eq(tipoPessoa));
			query.addOrder(AuditEntity.revisionNumber().desc());
			List<Fornecedor> fornecedores = query.getResultList();

			return fornecedores;

		} catch (Exception e) {
			return null;
		}
	}

}
