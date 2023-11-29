package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.LancamentoDAO;
import entidades.Lancamento;

@ManagedBean
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lista = new ArrayList<Lancamento>();

	public String salvar() {
		try {
			LancamentoDAO.salvar(lancamento);
			lancamento = new Lancamento();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Lancamento salvo com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}

		return null;
	}

	public String deletar(Lancamento l) {
		try {
			LancamentoDAO.remover(l);
			lancamento = new Lancamento();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Lancamento removido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}

		return null;
	}

	public void resumo() {
		try {
			double receita = 0.0;
			double despesas = 0.0;
			double saldo = 0.0;
			for (int i = 0; i < this.getLista().size(); i++) {
				switch (this.lista.get(i).getTipo()) {
				case "receita":
					receita += this.lista.get(i).getValor();
					break;
				case "despesa":
					despesas += this.lista.get(i).getValor();
					break;
				default:
				}
			}
			saldo = receita - despesas;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resumo",
					"Receita: " + receita + "\n" + "Despesas: " + despesas + "\n" + "Saldo: " + saldo));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public void showDetails(Lancamento l) {
		try {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalhes",
							"Lancamento:\n" + "Id: " + l.getId() + "\n" + "Descrição: " + l.getDescricao() + "\n"
									+ "Custo: " + l.getValor() + "\n" + "Tipo: " + l.getTipo() + "\n" + "Descrição: "
									+ l.getDescricao() + "\n" + "Data: " + l.getData()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Culpe o programador"));
		}
	}

	public List<Lancamento> getLista() {
		if (lista != null) {
			lista = LancamentoDAO.acharTodos();
		}
		return lista;
	}

	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}
}
