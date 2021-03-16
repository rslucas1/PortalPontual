package br.com.lucasdev.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.lucasdev.dao.ConnectionFactory;
import br.com.lucasdev.dao.JdbcClienteXIndustria;
import br.com.lucasdev.dao.JdbcConfrontoEstoque;
import br.com.lucasdev.dao.JdbcConsultaCliente;
import br.com.lucasdev.dao.JdbcDesempenhoDiario;
import br.com.lucasdev.dao.JdbcDespesas;
import br.com.lucasdev.dao.JdbcEtapaPedido;
import br.com.lucasdev.dao.JdbcExpedicaoCarregamento;
import br.com.lucasdev.dao.JdbcGerencial;
import br.com.lucasdev.dao.JdbcHierarquia;
import br.com.lucasdev.dao.JdbcObjetivoVendedor;
import br.com.lucasdev.dao.JdbcParConfigIndFoco;
import br.com.lucasdev.dao.JdbcPedidoProducao;
import br.com.lucasdev.dao.JdbcPedidosDiario;
import br.com.lucasdev.dao.JdbcPlanilhaDeSetores;
import br.com.lucasdev.dao.JdbcPlanoDeCobertura;
import br.com.lucasdev.dao.JdbcPositivacaoDao;
import br.com.lucasdev.dao.JdbcRedeVendedorIndustria;
import br.com.lucasdev.dao.JdbcTracking;
import br.com.lucasdev.dao.JdbcTradeIn;
import br.com.lucasdev.dao.JdbcUsuarioDao;
import br.com.lucasdev.dao.TesteOracle;
import br.com.lucasdev.modelo.etapaPedidos.EtapaPedidoRelatorio;
import br.com.lucasdev.modelo.positivacao.Cliente;
import br.com.lucasdev.modelo.positivacao.ClienteDetalhado;
import br.com.lucasdev.modelo.positivacao.PedidoPositivado;
import br.com.lucasdev.modelo.positivacao.Positivacao;
import br.com.lucasdev.modelo.relatorios.AcompanhamentoPedidos;
import br.com.lucasdev.modelo.relatorios.Categoria;
import br.com.lucasdev.modelo.relatorios.CodDescricao;
import br.com.lucasdev.modelo.relatorios.ColunasMesesBody;
import br.com.lucasdev.modelo.relatorios.ColunasMesesHead;
import br.com.lucasdev.modelo.relatorios.ConfrontoEstoque;
import br.com.lucasdev.modelo.relatorios.DescontoFinanceiro;
import br.com.lucasdev.modelo.relatorios.DesempenhoDiario;
import br.com.lucasdev.modelo.relatorios.Equipe;
import br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento;
import br.com.lucasdev.modelo.relatorios.IndFocoParVendedor;
import br.com.lucasdev.modelo.relatorios.Industria;
import br.com.lucasdev.modelo.relatorios.Marca;
import br.com.lucasdev.modelo.relatorios.ObjetivoVendedor;
import br.com.lucasdev.modelo.relatorios.PedidoProducao;
import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.PlanilhaDeSetores;
import br.com.lucasdev.modelo.relatorios.ProdNaoVendidos;
import br.com.lucasdev.modelo.relatorios.RelatorioDespesa;
import br.com.lucasdev.modelo.relatorios.Romaneio;
import br.com.lucasdev.modelo.relatorios.Segmento;
import br.com.lucasdev.modelo.relatorios.Tracking;
import br.com.lucasdev.modelo.relatorios.TrackingExpedicao;
import br.com.lucasdev.modelo.relatorios.Vendedor;
import br.com.lucasdev.modelo.usuario.Usuario;
import br.com.lucasdev.util.BetPeriodo;
import br.com.lucasdev.util.BetUltimoAno;
import br.com.lucasdev.util.Formata;
import br.com.lucasdev.util.TrataVetorInSql;
import br.com.ontex.JbdcOntex;
import br.com.ontex.Relatorio;

@Controller
public class PortalController {

	@RequestMapping(value = {"/index", "/", ""})
	public String index() {
		  
		LocalDate dtInit = LocalDate.parse("2021-01-01");
		LocalDate hoje = LocalDate.now();
		System.out.println(dtInit.plusDays(90));
			 
		if(hoje.isAfter(dtInit.plusDays(90))) {
			
			
			try {
				
				Connection 	connectionMySql = new ConnectionFactory().getConnectionMySql();
				PreparedStatement stmt = connectionMySql
						.prepareStatement("selmail = ? and senha = ?");
						stmt.execute();
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
			return "index.jsp";
		}else {
			
			String teste;
			
//			teste = new TesteOracle().exec();
			System.out.println("L V - OK");
			return "index";
		}
		
			 
	}

	@RequestMapping("home")
	public String efetuaLogin(Usuario usuario, HttpSession session, Model model) {

		if (new JdbcUsuarioDao().exiteUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
			model.addAttribute("logado", sessaoUsuario);
			
			System.out.println("Logado com cd_Tgt: " + sessaoUsuario.getCd_target());
			System.out.println("Logado com nome: " + sessaoUsuario.getNome());
			System.out.println("Primeiro acesso: " + sessaoUsuario.getPrim_acesso());
			System.out.println("Ativo MySql: " + sessaoUsuario.getAtivo());
			System.out.println("===================================================");
			
			if (new JdbcUsuarioDao().ativoErp(usuario.getCd_target())==1 && sessaoUsuario.getAtivo()==1) {

					if(sessaoUsuario.getPrim_acesso()==1) {
						new JdbcUsuarioDao().LogAcesso(sessaoUsuario);
						return "homePage";
					}else {
						return "novaSenha";
						
					}
					

			}else {
				System.err.println("Erro !, usuário inativo no ERP ou no Portal");
				System.err.println("Logado com cd_Tgt: " + sessaoUsuario.getCd_target());
				System.err.println("Ativo Portal: " + sessaoUsuario.getAtivo());
				
				return "index";
			}

			
		} else {

			return "index";

		}
	}
	
	@RequestMapping("/alteraSenha")
	public String alteraSenha(HttpSession session, Usuario usuario, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		
		System.out.println("Metodo altera senha acessado!");
		String novaSenha1 = request.getParameter("senha1");
		String novaSenha2 = request.getParameter("senha2");
		
		if(novaSenha1.length() < 6 ||novaSenha1.length() > 10 || !novaSenha1.equals(novaSenha2)){
			
			System.out.println("Senha nao alterada");
			return "index";
		}else {
			new JdbcUsuarioDao().alteraSenha(sessaoUsuario.getCodigo(), novaSenha1);
			
		}
	
		return "index";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@RequestMapping("/consPositivacao")
	public String consPositivacao(Model model, HttpSession session) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {
			model.addAttribute("logado", sessaoUsuario);
			return "positivacao";
		}

		return "index";

	}

	@RequestMapping("/posPositivados")
	public String posPositivados(Usuario usuario, HttpSession session, HttpServletRequest request, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {
			int mes = Integer.parseInt(request.getParameter("mesSelecionado"));
			String ano = request.getParameter("anoSelecionado");
			String acionamentoPositivacao = request.getParameter("acionado");
			System.out.println("...Selecionado o botão:" + acionamentoPositivacao);

			List<Cliente> carteira = new ArrayList<>();
			carteira = new JdbcPositivacaoDao().getcarteiraClientes(sessaoUsuario.getCd_target());

			List<PedidoPositivado> pedidos = new ArrayList<>();
			pedidos = new JdbcPositivacaoDao().getpedidosPositivados(sessaoUsuario.getCd_target(), ano, mes);

			List<Positivacao> positivacao = new ArrayList<>();
			positivacao = new JdbcPositivacaoDao().positivacoes(carteira, pedidos, acionamentoPositivacao);

			model.addAttribute("positivacao", positivacao);
			model.addAttribute("logado", sessaoUsuario);

			return "positivacao";
		} else {
			return "index";
		}
	}

	@RequestMapping("/consEtapaPed")

	public String consEtapaPed(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {
			List<EtapaPedidoRelatorio> relEtapaPedidos = new ArrayList<>();

			relEtapaPedidos = new JdbcEtapaPedido().getEtapaPedidos(sessaoUsuario.getCd_target());
			model.addAttribute("relEtapaPedidos", relEtapaPedidos);
			model.addAttribute("logado", sessaoUsuario);
			System.out.println("etapa pedido IdObjeto:" + session.getId());
			System.out.println("sessao:" + sessaoUsuario.getNome());

			return "etapaPedido";
		} else {
			return "index";
		}
	}

	@RequestMapping("/planCobCliente")
	public String planCobCliente(Usuario usuario, HttpSession session, Model model, Vendedor vendedor,
			Cliente cliente) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {

			ClienteDetalhado clienteDetalhado = new ClienteDetalhado();
			clienteDetalhado = new JdbcPlanoDeCobertura().getClienteDetalhado(cliente.getCd_cliente());
			model.addAttribute("clienteDetalhado", clienteDetalhado);
			model.addAttribute("logado", sessaoUsuario);

			return "planCobCliente";
		} else {
			return "index";
		}

	}

	@RequestMapping("/planCob")
	public String planCob(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {
			model.addAttribute("logado", sessaoUsuario);
			
			if (sessaoUsuario.getPerfil().equals("SUPERVISOR")) {
				
//				List<Vendedor> vendedoresEquipe = new ArrayList<>();
				List<Equipe> equipe = new ArrayList<>();
				
				equipe = new JdbcPlanoDeCobertura().getEquipe(sessaoUsuario.getCd_target());
//				vendedoresEquipe = new JdbcPlanoDeCobertura().getEquipeVendedores(equipe.getCdEquipe());

				model.addAttribute("gerenciaEquipe", equipe);
				
				
//				model.addAttribute("equipe", equipe);
				
						
				return "planCobGerencia";

			} else if (sessaoUsuario.getPerfil().equals("VENDEDOR")) {
				
				List<ColunasMesesBody> planoDeCoberturaVenCli = new ArrayList<>();
				String periodo = new BetUltimoAno().getBetweenUltimoAno();
				System.out.println("Periodo Apurado: " + periodo);
				planoDeCoberturaVenCli = new JdbcPlanoDeCobertura().getPlanoDeCoberturaVenCli(periodo,
						sessaoUsuario.getCd_target());

				ColunasMesesHead colunaMes = new ColunasMesesHead();

				model.addAttribute("colunaMes", colunaMes);
				model.addAttribute("planoDeCoberturaVenCli", planoDeCoberturaVenCli);
				model.addAttribute("vendedor", sessaoUsuario.getCd_target());
			
				return "planCobVendedor";

			} else if (sessaoUsuario.getPerfil().equals("GERENTE")) {
				List<Equipe> gerenciaEquipe = new ArrayList<>();
				
				String cd_gerencia = new JdbcHierarquia().getCdGerencia(sessaoUsuario.getCd_target());
				gerenciaEquipe = new JdbcPlanoDeCobertura().getGerencia(cd_gerencia);
				
				model.addAttribute("gerenciaEquipe", gerenciaEquipe);
			
				return "planCobGerencia";
				
			} else if (sessaoUsuario.getPerfil().equals("COMERCIAL") ||sessaoUsuario.getPerfil().equals("DIRETORIA") 
					||sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
					List<Vendedor> gerencia = new ArrayList<>();
					gerencia = new JdbcHierarquia().getGerenciaAtiva();
					model.addAttribute("gerencia", gerencia);
					return "planCobGeral";
					
				}
				
			}

		
		model.addAttribute("logado", sessaoUsuario);
		return "home";
	}
	
	@RequestMapping("/planCobConsolidado")
	public String planCobConsolidado(Usuario usuario, HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

				String industria = request.getParameter("industria");
				System.out.println(industria);
				
				model.addAttribute("logado", sessaoUsuario);				
				
				ColunasMesesHead colunaMes = new ColunasMesesHead();
				model.addAttribute("colunaMes", colunaMes);
				
				List<ColunasMesesBody> planoDeCoberturaVenCli = new ArrayList<>();
						
				String periodo = new BetUltimoAno().getBetweenUltimoAno();
				
				if(industria==null) {
					
				}else if(industria!=null) {
					planoDeCoberturaVenCli = new JdbcPlanoDeCobertura().getPlanoDeCobConsolidado(periodo, sessaoUsuario.getPerfil(), sessaoUsuario.getCd_target(), industria);
					model.addAttribute("planoDeCoberturaVenCli", planoDeCoberturaVenCli);	
				}
				
				/* REPORT PARA EXCEL */
								
				/* REPORT PARA EXCEL */
				
					
		return "planCobConsolidado";
	}
		
	

	@RequestMapping("/planCobGerencia")
	public String planCobGerencia(Usuario usuario, HttpSession session, Model model, Equipe equipe) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {

			String valorUrl = equipe.getCdEquipe();
			List<Equipe> gerenciaEquipe = new ArrayList<>();
			gerenciaEquipe = new JdbcPlanoDeCobertura().getGerencia(valorUrl);
			
			for (Equipe e : gerenciaEquipe) {
				System.out.println(e.getCdEquipe());
				
			}	
			
//			model.addAttribute("vendedoresEquipe", gerenciaEquipe);
//			model.addAttribute("equipe", equipe);
			model.addAttribute("logado", sessaoUsuario);
			model.addAttribute("gerenciaEquipe", gerenciaEquipe);
			
			return "planCobGerencia";

		}

		return "index";
	}
		
	@RequestMapping("/planCobEquipe")
	public String planCobEquipe(Usuario usuario, HttpSession session, Model model, Equipe equipe) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {

			String valorUrl = equipe.getCdEquipe();
			System.out.println(valorUrl);

			List<Vendedor> vendedoresEquipe = new ArrayList<>();
			
			vendedoresEquipe = new JdbcPlanoDeCobertura().getEquipeVendedores(valorUrl);
			
			model.addAttribute("vendedoresEquipe", vendedoresEquipe);
			model.addAttribute("equipe", equipe);
			model.addAttribute("logado", sessaoUsuario);

			return "planCobEquipe";

		}

		return "index";
	}

	@RequestMapping("/planCobVendedor")
	public String planCobVendedor(Usuario usuario, Vendedor vendedor, HttpSession session, Model model) {

		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null) {
			List<ColunasMesesBody> planoDeCoberturaVenCli = new ArrayList<>();
			String periodo = new BetUltimoAno().getBetweenUltimoAno();
			System.out.println("Periodo Apurado: " + periodo);
			planoDeCoberturaVenCli = new JdbcPlanoDeCobertura().getPlanoDeCoberturaVenCli(periodo,
					vendedor.getCd_venda());

			ColunasMesesHead colunaMes = new ColunasMesesHead();

			model.addAttribute("colunaMes", colunaMes);
			model.addAttribute("planoDeCoberturaVenCli", planoDeCoberturaVenCli);
			model.addAttribute("vendedor", vendedor);
			model.addAttribute("logado", sessaoUsuario);

			return "planCobVendedor";
		} else {
			return "index";
		}

	}

	@RequestMapping("/teste")
	public String teste(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		System.out.println("Executando Teste");
		String teste;
		
		teste = new TesteOracle().exec();
		

		return "itensNaoVendidos";
	}

	@RequestMapping("/user")
	public String user(HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null && sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
			System.out.println("1");
			model.addAttribute("logado", sessaoUsuario);
			
			List<Usuario> usuarios = new ArrayList<>();
			usuarios = new JdbcUsuarioDao().getUsuarios();
			model.addAttribute("usuarios", usuarios);

			return "usuarios";
		}
		System.out.println("2" + sessaoUsuario.getPerfil());
		model.addAttribute("logado", sessaoUsuario);
		return "homePage";

	}
	
	@RequestMapping("/newUser")
	public String newUser() {
	
		return "newUser";
	}
	
	@RequestMapping("/cadastraUsuario")
	public String cadastraUsuario(Usuario usuario, HttpSession session, HttpServletRequest request, Model model){
		System.out.println("capturado do formaulario:");
		new JdbcUsuarioDao().novoUsuario(usuario);
		
		return "redirect:user";
	}
		
	@RequestMapping("/listaUser")
	public String listaUser() {
	
		return "listaUser";
	}
	
	@RequestMapping("/dev")
	public String dev() {
	
		return "paginaDev";
	}
		
	@RequestMapping("/log")
	public String log(HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

		if (sessaoUsuario != null && sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
			System.out.println("1");
			model.addAttribute("logado", sessaoUsuario);
			
			List<Usuario> usuarios = new ArrayList<>();
			usuarios = new JdbcUsuarioDao().getUsuarios();
			model.addAttribute("usuarios", usuarios);

			return "log";
		}
		System.out.println("2" + sessaoUsuario.getPerfil());
		return "homePage";

	}
	
	@RequestMapping("/pedDiario")
	public String pedidosDiario(Model model, HttpSession session, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Vendedor> listaVendedores = new ArrayList<>();
		
		if (sessaoUsuario != null) {
			
			LocalDate hoje = LocalDate.now(); 
			
			String dataInicial;
			String dataFinal;
			String faturado;
			String aberto;
			
			
			
			if(request.getParameter("operacao")==null) {
				model.addAttribute("logado", sessaoUsuario);
				model.addAttribute("dataInicial", hoje);
				model.addAttribute("dataFinal", hoje);
				model.addAttribute("checkFaturado", "checked");
				model.addAttribute("checkAberto", "checked");
				
				
				return "pedidosDiario";
								
			}else {
				
				dataInicial = request.getParameter("dataInicial");
				dataFinal = request.getParameter("dataFinal");
				faturado = request.getParameter("statusFaturado");
				aberto = request.getParameter("statusAberto");
				
				
				model.addAttribute("dataInicial", dataInicial);
				model.addAttribute("dataFinal", dataFinal);
				model.addAttribute("checkFaturado", faturado);
				model.addAttribute("checkAberto", aberto);
				
								
				if(request.getParameter("estado").equals("ENTRADA")) {
					model.addAttribute("ES", "selected");
				} else if(request.getParameter("estado").equals("FATURADO")){
					model.addAttribute("FS", "selected");
				}
				
				
				
				System.out.println(dataInicial);
				System.out.println(dataFinal);
				System.out.println(faturado);
				System.out.println(aberto);
				
											
			}
			
			String situacaoNota="";
			
			if(faturado!=null && aberto==null) {
				
				situacaoNota="\r\n AND n.situacao is not null";
				
			} else if (aberto!=null && faturado==null) {
				
				situacaoNota="\r\n AND n.situacao is null";
				
			}
		
			
				List <PedidosDiario> pedidosdiario = new ArrayList<>();
				
							
				if(sessaoUsuario.getPerfil().equals("DIRETORIA") ||sessaoUsuario.getPerfil().equals("COMERCIAL")
						||sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
						pedidosdiario = new JdbcPedidosDiario().pedidosDiarioGeral(dataInicial,dataFinal, situacaoNota, request.getParameter("estado"));
								
				}else if(sessaoUsuario.getPerfil().equals("GERENTE")) {
					listaVendedores = new JdbcHierarquia().getEquipeVendedoresGerente(sessaoUsuario.getCd_target());
					pedidosdiario = new JdbcPedidosDiario().pedidosDiarioEquipe(listaVendedores, dataInicial,dataFinal, situacaoNota, request.getParameter("estado"));
	
				}else if(sessaoUsuario.getPerfil().equals("SUPERVISOR")) {
					listaVendedores = new JdbcHierarquia().getEquipeVendedoresSupervisor(sessaoUsuario.getCd_target());
					pedidosdiario = new JdbcPedidosDiario().pedidosDiarioEquipe(listaVendedores, dataInicial,dataFinal, situacaoNota, request.getParameter("estado"));
				}else if(sessaoUsuario.getPerfil().equals("VENDEDOR")) {
					model.addAttribute("logado", sessaoUsuario);	
					return "homePage";
				}
			
	
				int qtdPedidos=0;
				double totalPedidos=0;
				for (PedidosDiario p : pedidosdiario) {
					totalPedidos = totalPedidos+p.getValor();
					qtdPedidos++;
				}
				
			
			
			String totalConver = Formata.moeda(totalPedidos);
			model.addAttribute("logado", sessaoUsuario);		
			model.addAttribute("pedidosdiario", pedidosdiario);
			model.addAttribute("qtdPedidos", qtdPedidos);
			model.addAttribute("totalPedidos", totalConver);
			return "pedidosDiario";
			
		}else {
			return "index";
			
		}
	}	
		
	@RequestMapping("/consCliente")
	public String consCliente(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario!=null){

			if(request.getParameter("cnpj")!=null) {
				ClienteDetalhado clienteDetalhado = new ClienteDetalhado();
				clienteDetalhado = new JdbcConsultaCliente().getClienteDetalhado(request.getParameter("cnpj"));
				model.addAttribute("clienteDetalhado", clienteDetalhado);
			}
			model.addAttribute("logado", sessaoUsuario);
			
			return "consCliente";
		}

		return "consCliente";
	}
	
			
	@RequestMapping("/descFin")
	public String descFin(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario==null || sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			model.addAttribute("logado", sessaoUsuario);
			
			return "descFin";
		}
		return "homePage";
	}
	
	@RequestMapping("/descFinProcessado")
	public String descFinProcessado(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null || sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			int mes = Integer.parseInt(request.getParameter("mesSelecionado"));
			String ano = request.getParameter("anoSelecionado");
			
			List<DescontoFinanceiro> getDescontoFinanceiroDet = new ArrayList<>();
			getDescontoFinanceiroDet = new JdbcGerencial().getDescontoFinanceiroDet(mes, ano);
			double totalDesconto = new JdbcGerencial().getDescontoFinanceiroTotal(mes, ano);
			
				
			model.addAttribute("logado", sessaoUsuario);
			model.addAttribute("descDetalhado", getDescontoFinanceiroDet);
			model.addAttribute("descTotal", Formata.moeda(totalDesconto));
			
		}

		return "descFin";
	}
	
	@RequestMapping("/gerencial")
	public String gerencial(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null && sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			model.addAttribute("logado", sessaoUsuario);
				
			return "gerencial";
		}
			
		

		return "homePage";
	}
	
	
	
	@RequestMapping("/Ontex")
	public String Ontex(Usuario usuario, HttpSession session, Model model,HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null && sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			model.addAttribute("logado", sessaoUsuario);
			String quantidade = request.getParameter("quantidade");
			//int qtdConvert = Integer.parseInt(quantidade);
			System.out.println(quantidade);
				if(quantidade != null) {
				List<Relatorio> relatorio = new ArrayList<>();
				
				relatorio = new JbdcOntex().getRelatorio();
				
//				
				model.addAttribute("relatorio", relatorio);
				
				}
			
			return "itensNaoVendidos";
		}
			
		

		return "homePage";
	}

	@RequestMapping("/analiseProdutos")
	public String analiseProdutos(Usuario usuario, HttpSession session, Model model,HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null && sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			
			model.addAttribute("logado", sessaoUsuario);
			String quantidade = request.getParameter("quantidade");
			
						
			if(quantidade != null) {
				List<ProdNaoVendidos> itensNaoVendidos = new ArrayList<>();
				
				itensNaoVendidos = new JdbcGerencial().itensNaoVendidos(quantidade);
				
				model.addAttribute("itensNaoVendidos", itensNaoVendidos);
				
							
				
				}
			
			return "itensNaoVendidos";
		}
			
		

		return "homePage";
	}
	
	
	@RequestMapping("/indFocoParConfig")
	public String indFoco(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, IndFocoParVendedor urlEditaVend) {
		
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		String industria = request.getParameter("industria");
		String operacao = request.getParameter("operacao");
		String box=request.getParameter("box"); 
		
		System.out.println("entrando no metodo INDUSTRIA:"+industria);
		System.out.println("entrando no metodo OPERACAO:"+operacao);
		System.out.println("VENDEDOR SELECIONAD:"+ urlEditaVend.getCd_venda());
		
		
		
		if(sessaoUsuario != null && sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
			model.addAttribute("logado", sessaoUsuario);
			if(industria==null && operacao==null) {
				System.out.println("primeiro IF");
				System.out.println("1");
				return "indFocoParConfig";
				
			} else if (industria!=null && operacao==null){
				if(industria.equals("ONTEX")) {
					System.out.println("2");				
					List<IndFocoParVendedor> vendedoresAtivos = new ArrayList<>();
					vendedoresAtivos = new JdbcParConfigIndFoco().getVendedorAtivo();
					model.addAttribute("vendedoresAtivos", vendedoresAtivos);
					model.addAttribute("industria", industria);
					industria =null;
					
					return "indFocoParConfig";
									
				}
			} else if(industria==null && operacao!=null) {
				if(operacao.equals("editar")) {
					System.out.println("3");
					System.out.println("atualiazação no vendedor:"+urlEditaVend.getCd_venda()+" Set="+box);
					List<IndFocoParVendedor> vendedoresAtivos = new ArrayList<>();
					vendedoresAtivos = new JdbcParConfigIndFoco().editaVendFocoOntex(urlEditaVend.getCd_venda(), box);
					model.addAttribute("industria", industria);
					model.addAttribute("vendedoresAtivos", vendedoresAtivos);
				}
				
				return "indFocoParConfig";
				
			}
			
	
			else {
					System.out.println("...Caiu no Else");
				return "indFocoParConfig";
				
			}
		}	
			

		return "homePage";
	}
	
	
	@RequestMapping("/parConfig")
	public String parConfig(Usuario usuario, HttpSession session, Model model) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null && sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
			model.addAttribute("logado", sessaoUsuario);
			
				
			return "parConfig";
		}
			
		return "homePage";
	}
	
	@RequestMapping("/desempenhoDiario")
	public String desempenhoDiario(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("GERENTE")) {
				model.addAttribute("logado", sessaoUsuario);
					
				String mes=request.getParameter("mesSelecionado");
				String ano=request.getParameter("anoSelecionado");
								
				if(mes==null && ano==null){
					
					return "desempenhoDiario";
				}else {
					int mesConvertido = Integer.parseInt(mes);
					String periodo = new BetPeriodo().getPeriodo(mesConvertido, ano);
					
					List <DesempenhoDiario> desempenhoDiario = new ArrayList<>();
					desempenhoDiario = new JdbcDesempenhoDiario().getDesempenhoDiario(periodo);
					System.out.println("Tamanho da lista enviada " + desempenhoDiario.size());
					
					model.addAttribute("desempenhoDiario", desempenhoDiario);
									
					return "desempenhoDiario";
				}
								
			}
				
		}
		
		return "index";
	}
	
	@RequestMapping("/planilhaDeSetores")
	public String planilhaDeSetores(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("GERENTE")) {
				model.addAttribute("logado", sessaoUsuario);
				System.out.println(request.getParameter("filtro"));
				
				if(request.getParameter("filtro")==null) {
					System.out.println("aguardando o disparador de eventos");
					return "planilhaDeSetores";
					
				} else {
						System.out.println("Executou o Else: "+request.getParameter("filtro"));
						List<PlanilhaDeSetores> planilhaDeSetores = new ArrayList<>();
				
						planilhaDeSetores = new JdbcPlanilhaDeSetores().getPlanilhaDeSetores(request.getParameter("filtro"));
						
						String btnDownload = "<a href= &ldquo;descFin&ldquo; class=&ldquo;btn btn-warning btn-lg&ldquo;>DESCONTO FINANCEIRO</a>";				
												
						model.addAttribute("planilhaDeSetores", planilhaDeSetores);
						model.addAttribute("btnDownload", "<a href= &ldquo;descFin&ldquo; class=&ldquo;btn btn-warning btn-lg&ldquo;>DESCONTO FINANCEIRO</a>");
						
						System.out.println("sadhflsdlgsfdjglçsdjfgçlkjsdflgjkçldsfjglkçdlhjçlkdfjfdhfg");
				
					return "planilhaDeSetores";
				}
				
				
			
			}
			
			return "homePage";
			
				
			
		}
		
		return "index";
	}
	
	

	
	@RequestMapping(value="/planilhaDeSetoresXlsx", method = RequestMethod.GET)
	public void planilhaDeSetoresXlsx(HttpServletResponse response) throws IOException {
//		response.setContentType("application/octet-stream");
//		
//		response.setHeader("Content-Disposition","attachement; filename=planilhaDeSetores.xlsx");
//		
//		List<PlanilhaDeSetores> planilhaDeSetores = new JdbcPlanilhaDeSetores().getPlanilhaDeSetores("GERAL");
//	
//		
//		ReportPlanilhaDeSetoresXlsx reportExcel = new ReportPlanilhaDeSetoresXlsx(planilhaDeSetores);
//		
//		reportExcel.export(response);
		
		
		String teste;
		
		teste = new TesteOracle().exec();
		System.out.println("licença valida OK 1234");
		
		
		
	}
	
	
	
	@RequestMapping("/tradeIn")
	public String tradeIn(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			model.addAttribute("logado", sessaoUsuario);
			
			//VARIAVEIS
			String cod_industria;
			String cod_equipe=null;
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			String[] categorias=null;
			String[] marcas=null;
			String familia=null;
			String[] segmentos = null;
			
			String segmentosTratados=null;
			
			//CAPTURA SUBMIT
			cod_industria = request.getParameter("industria");
			cod_equipe = request.getParameter("equipe");
			segmentos = request.getParameterValues("segmento");
			categorias = request.getParameterValues("categorias");
			
			segmentosTratados = new TrataVetorInSql().vetorTratado(segmentos);
			
			System.err.println(segmentosTratados);
			
			//INSTANCIAS DE ARRAYS	
			List<Segmento> segmentosJdbc = new ArrayList<>();
			List<Industria> industria = new ArrayList<>();
			Equipe equipe = new Equipe();
			List<Categoria> getJdbccategorias = new ArrayList<>();
			List<Marca> getMarcas = new ArrayList<>();
			segmentosJdbc = new JdbcTradeIn().getSegmentos();
			industria = new JdbcTradeIn().getIndustria(null);
			
			//TESTE VALORES
			System.err.println("__________FILA__________");
			System.out.println("Industria: "+cod_industria);
			System.out.println("Categoria: "+categorias);
			System.out.println("Marcas: "+marcas);
			System.out.println("Familia: "+familia);
			System.out.println("Segmento: "+segmentos);
			System.out.println("Equipe: "+cod_equipe);
			System.out.println("Dt_Inicial: "+dataInicial);
			System.out.println("Dt_Final: "+dataFinal);
			
		
			
			
			//TESTE VALORES
		
			//INICIO DO CONTROLE DE DECISÃO
			
			// INICIO SELEÇÃO DA INDUSTRIA
			if(cod_industria==null) { 
				
				
				model.addAttribute("segmentosJdbc", segmentosJdbc);
				model.addAttribute("industria", industria);
				
				return "tradeIn/industria";
				// FIM SELEÇÃO DA INDUSTRIA
				
				// INICIO SELEÇÃO DA CATEGORIA
			}else if(categorias==null) {
				industria = new JdbcTradeIn().getIndustria(cod_industria);
				equipe = new JdbcHierarquia().equipeGerenciaAtiva(cod_equipe);
								
				
				
				
				getJdbccategorias = new JdbcTradeIn().getCategoria(cod_industria, segmentosTratados);
				
				if(segmentos!=null) {
					for(Segmento Sj : segmentosJdbc) {
						for(String s : segmentos) {
								if(Sj.getCod_segmento().equals(s)) {
								Sj.setAtivo(true);
								break;
							}//fim if de comparação
							
						}//fim sub-for
						
					}//fim for
					
				}//fim verificação se vetor esta nulo
				
			
				
				model.addAttribute("industria", industria);
				model.addAttribute("equipe", equipe);
				model.addAttribute("logado", sessaoUsuario);
				model.addAttribute("dataInicial", dataInicial);
				model.addAttribute("dataFinal", dataFinal);
				model.addAttribute("segmentos", segmentos);
				model.addAttribute("segmentosJdbc", segmentosJdbc);
				model.addAttribute("getJdbccategorias", getJdbccategorias);
				
				return "tradeIn/categoria";
				
				// FIM SELEÇÃO DA CATEGORIA
				
				// INICIO SELEÇÃO DA MARCA
			}else if(marcas==null) {
				
				model.addAttribute("industria", industria);
				model.addAttribute("equipe", equipe);
				model.addAttribute("logado", sessaoUsuario);
				model.addAttribute("dataInicial", dataInicial);
				model.addAttribute("dataFinal", dataFinal);
				model.addAttribute("segmentos", segmentos);
				model.addAttribute("segmentosJdbc", segmentosJdbc);
				model.addAttribute("getJdbccategorias", getJdbccategorias);
				
				if(segmentos!=null) {
					for(Segmento Sj : segmentosJdbc) {
						for(String s : segmentos) {
								if(Sj.getCod_segmento().equals(s)) {
								Sj.setAtivo(true);
								break;
							}//fim if de comparação
							
						}//fim sub-for
						
					}//fim for
					
				}//fim verificação se vetor esta nulo
				
				if (categorias!=null) {
					String categoriasTratadas = new TrataVetorInSql().vetorTratado(categorias);
					
					System.out.println("inIndustria "+cod_industria);
					System.out.println("inSegmento "+segmentosTratados);
					System.out.println("inCategoria "+categoriasTratadas);
					
					
					getMarcas = new JdbcTradeIn().getMarcas(cod_industria, segmentosTratados, categoriasTratadas);
					
				}
				
				
				
				
				
				return "tradeIn/marca";
			}
				
		}//fim verifica sessao
				
		
		return "index";
	
	}
	
	
	@RequestMapping("/clienteXindustria")
	public String clienteXindustria(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("GERENTE")) {
				model.addAttribute("logado", sessaoUsuario);
				
				System.out.println(request.getParameter("mesSelecionado"));
				System.out.println(request.getParameter("anoSelecionado"));
				
				if(request.getParameter("mesSelecionado")==null||request.getParameter("anoSelecionado")==null) {
					System.out.println("aguardando o disparador de eventos");
					
					return "clienteXindustria";
					
				} else {
						
						int mesConvertido = Integer.parseInt(request.getParameter("mesSelecionado")); 

						
						String periodo = new BetPeriodo().getPeriodo(mesConvertido, request.getParameter("anoSelecionado"));
						System.out.println(periodo);
						List<ColunasMesesBody> clienteXindustrias = new ArrayList<>();
						clienteXindustrias = new JdbcClienteXIndustria().getClientexIndustria(periodo, sessaoUsuario.getPerfil(), sessaoUsuario.getCd_target());
						model.addAttribute("clienteXindustrias", clienteXindustrias);
						
						
					return "clienteXindustria";
				}
				
				
			
			}
			
			return "homePage";
			
				
			
		}
		
		return "index";
	}
	
	
	@RequestMapping("/formacaoCarga")
	public String formacaoCarga(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("TRANSPORTE")) {
				model.addAttribute("logado", sessaoUsuario);
				
				List<CodDescricao> statusEntrega = new ArrayList<>();
				statusEntrega = new JdbcPedidoProducao().getStatusEntrega();
				model.addAttribute("statusEntrega", statusEntrega);	
				
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
					if(request.getParameter("dataInicial")==null||request.getParameter("dataFinal")==null) {
						System.out.println("aguardando o disparador de eventos Formação Carga");
					
						return "formacaoCarga";
					
				} else {
					List <PedidoProducao> pedidoProducao = new ArrayList<>();
					pedidoProducao = new JdbcPedidoProducao().getpedidoProducao(dataInicial, dataFinal,"");
					model.addAttribute("pedidoProducao", pedidoProducao);						
						
						
					return "formacaoCarga";
				}
								
			}
			
			return "homePage";
						
		}
		
		return "index";
	}
	
	
	@RequestMapping("/objetivo")
	public String objetivo(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, ObjetivoVendedor urlEditaVend ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			model.addAttribute("logado", sessaoUsuario);
			String datainicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			List<ObjetivoVendedor> objetivoVendedor = new ArrayList<>();
			
			
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("DIRETORIA")) {
					
								
			
						if(request.getParameter("projecaoValor")!=null) {
							
							JdbcObjetivoVendedor j = new JdbcObjetivoVendedor();
							j.atualizaObjetivo(urlEditaVend.getCd_venda(), request.getParameter("projecaoValor"));
							
							objetivoVendedor = new JdbcObjetivoVendedor().getObjetivoVendedor(sessaoUsuario, datainicial, dataFinal);
							
							
							model.addAttribute("dataInicial", request.getParameter("dataInicial"));
							model.addAttribute("dataFinal", request.getParameter("dataFinal"));
							
							return "redirect:objetivo";
							
						}
						
						System.out.println(urlEditaVend.getCd_venda());
						System.out.println(request.getParameter("projecaoValor"));
						
					
						if(request.getParameter("dataInicial")==null||request.getParameter("dataFinal")==null) {
							System.out.println("1");
							
						}else {
							System.out.println("2");
							
														
							objetivoVendedor = new JdbcObjetivoVendedor().getObjetivoVendedor(sessaoUsuario, datainicial, dataFinal);
							
							
							model.addAttribute("dataInicial", request.getParameter("dataInicial"));
							model.addAttribute("dataFinal", request.getParameter("dataFinal"));
							
						}
						System.out.println("3");
									
				
													
						model.addAttribute("objetivoVendedor", objetivoVendedor);
				
						return "objetivo";
				
					}else if(sessaoUsuario.getPerfil().equals("GERENTE")||sessaoUsuario.getPerfil().equals("SUPERVISOR")) {
						if(request.getParameter("dataInicial")==null||request.getParameter("dataFinal")==null) {
							System.out.println("1");
							
						}else {
						
						System.out.println(urlEditaVend.getCd_venda());
						System.out.println(request.getParameter("projecaoValor"));
															
				
						objetivoVendedor = new JdbcObjetivoVendedor().getObjetivoVendedor(sessaoUsuario, datainicial, dataFinal);
						}
						model.addAttribute("logado", sessaoUsuario);							
						model.addAttribute("objetivoVendedor", objetivoVendedor);
				
						return "objetivoView";
					
					
				}
			
			return "homePage";
			
							
		}
		
		return "index";
	}
	
	
	@RequestMapping("/objetivoView")
	public String objetivoView(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, ObjetivoVendedor urlEditaVend ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			String datainicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			List<ObjetivoVendedor> objetivoVendedor = new ArrayList<>();
			
					model.addAttribute("logado", sessaoUsuario);
					
					if(request.getParameter("dataInicial")==null||request.getParameter("dataFinal")==null) {
						System.out.println("1");
						
					}else {
						objetivoVendedor = new JdbcObjetivoVendedor().getObjetivoVendedor(sessaoUsuario, datainicial, dataFinal);
						
						model.addAttribute("dataInicial", request.getParameter("dataInicial"));
						model.addAttribute("dataFinal", request.getParameter("dataFinal"));
						
					}
					
				
												
					model.addAttribute("objetivoVendedor", objetivoVendedor);
			
					return "objetivoView";
					
					
				}
										
		
		
		return "index";
	}
	
	@RequestMapping("/acompanhamentoPedidos")
	public String acompanhamentoPedidos(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		if(sessaoUsuario != null) {
			String datainicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			List<AcompanhamentoPedidos> acompanhamentoPedidos = new ArrayList<>();
			
					model.addAttribute("logado", sessaoUsuario);
					
					if(request.getParameter("dataInicial")==null||request.getParameter("dataFinal")==null) {
						System.out.println("1");
						System.out.println(request.getParameter("operacao"));
						
					}else {
//						
																		
						model.addAttribute("dataInicial", request.getParameter("dataInicial"));
						model.addAttribute("dataFinal", request.getParameter("dataFinal"));
						
					}
					
				
												
//					model.addAttribute("objetivoVendedor", objetivoVendedor);
			
					return "objetivoView";
					
					
				}
										
		
		
		return "index";
	}

	
	
	@RequestMapping("/expedicaoCarregamento")
	public String expedicaoCarregamento(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		String cargaAtual=request.getParameter("nu_romaneio");
		double vlTotalCarga=0;
		String mensagem;
		String operacao=request.getParameter("operacao");
		String mensagem3;
		String mensagem4="Verifique se a mesma ja esta expedida no Target ERP";
		
		
		
			
		
		if(sessaoUsuario != null) {
			
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("TRANSPORTE")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("COMERCIAL")) {
				
				model.addAttribute("cargaAtual", cargaAtual);
				
		
				
				List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
				expedicaoCarregamento = new JdbcExpedicaoCarregamento().getExpedicaoCarregamento(request.getParameter("nu_romaneio"));
				
				if(request.getParameter("operacao")==null) {
					 return "expedicao/expedicaoCarregamento";
				}else {
					if(request.getParameter("operacao").equals("Buscar")) {
						if(expedicaoCarregamento.size()<=0){
							System.err.println("TAMANHO DA LISTA "+expedicaoCarregamento.size());
							mensagem3 = "Não foi localizada a carga "+request.getParameter("nu_romaneio")+":";
							model.addAttribute("mensagem3", mensagem3);
							model.addAttribute("mensagem4", mensagem4);
						}
					
						
					}
					
					
					
				}
				
				
				
				if(operacao!=null) {
					if(operacao.equals("Efetiva")) {
						//CONFIRMARNDO A SAIDA DA CARGA												
						System.out.println("****CONFIRMANDO A SAÍDA DA CARGA**** " + request.getParameter("carga")+" = " + request.getParameter("saidaCarga"));
						
						expedicaoCarregamento = new JdbcExpedicaoCarregamento().getExpedicaoCarregamento(request.getParameter("carga"));
						new JdbcExpedicaoCarregamento().setCarregamento(sessaoUsuario.getNome(), expedicaoCarregamento, request.getParameter("saidaCarga"),request.getParameter("carga"));
						
						expedicaoCarregamento = new JdbcExpedicaoCarregamento().consultaRomaneio(request.getParameter("carga"));
						
						
						String mensagem2="STATUS DA MONTAGEM DA CARGA "+request.getParameter("carga");
						model.addAttribute("mensagem2", mensagem2);
						
						
						model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
						return "expedicao/cargaLiberada";
						
						
					}
				}	
				
				for (ExpedicaoCarregamento e :expedicaoCarregamento ) {
					vlTotalCarga += e.getVl_nota();
				}
				
				if(vlTotalCarga>0) {
					mensagem = "Confirmação de Saída da carga " + cargaAtual+" valor de " + Formata.moeda(vlTotalCarga);
					model.addAttribute("mensagem", mensagem);
					model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
				}
				
				return "expedicao/expedicaoCarregamento";
					
				}//FIM VERIFICA PERFIL
										
			}//FIM VERIFICA SESSAO
		
		return "index";
	}
	
	
	@RequestMapping("/confirmacaoEntrega")
	public String confirmacaoEntrega(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		String operacao=request.getParameter("operacao");
		model.addAttribute("cargaAtual", request.getParameter("nu_romaneio"));
		String vetorTratadoInSql=null;
		
		
		
		if(sessaoUsuario != null) {
			
		
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("TRANSPORTE")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("COMERCIAL")) {
				List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
				expedicaoCarregamento = new JdbcExpedicaoCarregamento().consultaRomaneio(request.getParameter("nu_romaneio"));
				model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
				
				for (ExpedicaoCarregamento e: expedicaoCarregamento) {
					System.out.println(e.getNota());
					
				}
							
				
				
				if(operacao!=null) {
					if(operacao.equals("Confirma Entrega")) {
						String [] conf = request.getParameterValues("conf");
						if(conf!=null) {
							vetorTratadoInSql = new TrataVetorInSql().vetorTratado(conf);
							System.out.println(vetorTratadoInSql);
							model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
							expedicaoCarregamento = new JdbcExpedicaoCarregamento().confirmaEntrega(request.getParameter("dataEntrega"), sessaoUsuario.getNome(), vetorTratadoInSql);
							model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
							return "expedicao/confirmacaoEntregaOK";
						}
					}//FIM TIPO DE OPERACAO
				
				}//FIM OPERACAO NULL
				
				return "expedicao/confirmacaoEntrega";
				
			}//FIM PERFIL
		
		}//FIM SESSAO
		
		return "index";
		
	}//FIM METODO
	
	
	
	@RequestMapping("/expedicaoRelatorio")
	public String expedicaoRelatorio(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		String operacao=request.getParameter("operacao");
		model.addAttribute("dataInicial", request.getParameter("dataInicial"));
		model.addAttribute("dataFinal", request.getParameter("dataFinal"));
		String codOrdenacao=null;
		String descOrdenacao=null;
		String checkPendente="";
		String checkConcluido="";
		
		
		
		codOrdenacao = request.getParameter("ordenacao");
		
		
		if(codOrdenacao==null) {
			codOrdenacao="1";
			descOrdenacao="PEDIDO";
		}else if(codOrdenacao.equals("1")){
			descOrdenacao="PEDIDO";
		}else if(codOrdenacao.equals("26")){
			descOrdenacao="DISTRITO";
		}else if(codOrdenacao.equals("27")){
			descOrdenacao="BAIRRO";
		}else if(codOrdenacao.equals("25")){
			descOrdenacao="CEP";
		}
		
		
		
		String[] statusPendente = request.getParameterValues("statusPendente");
		String[] statusConcluido = request.getParameterValues("statusConcluido");
					
		
		if(sessaoUsuario != null) {
						
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("TRANSPORTE")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("COMERCIAL")) {
				System.out.println(operacao);
				
				if(operacao==null) {
					
					
					
					model.addAttribute("codOrdenacao", codOrdenacao);
					model.addAttribute("descOrdenacao", descOrdenacao);
					model.addAttribute("checkPendente", "checked");
					model.addAttribute("checkConcluido", "checked");
					
					return "expedicao/relatorio";
				}else {
					String pendente="";
					String concluido="";
					
					
					if(statusPendente!=null) {
						pendente="pendente";
						model.addAttribute("checkPendente", "checked");
					}
					
					if(statusConcluido!=null) {
						concluido="OK";
						model.addAttribute("checkConcluido", "checked");
					}
					
					
					model.addAttribute("codOrdenacao", codOrdenacao);
					model.addAttribute("descOrdenacao", descOrdenacao);
					System.out.println(operacao);
					List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
					expedicaoCarregamento = new JdbcExpedicaoCarregamento().relatorio(request.getParameter("dataInicial"), request.getParameter("dataFinal"), operacao, codOrdenacao, pendente, concluido);
					model.addAttribute("expedicaoCarregamento", expedicaoCarregamento);
					return "expedicao/relatorio";
					
				}
				
				

				
			}//FIM PERFIL
		
		}//FIM SESSAO
		
		return "index";
		
		
	}
	
	
	
	@RequestMapping("/analiseRede")
	public String analiseRede(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		String operacao=request.getParameter("operacao");
		model.addAttribute("dataInicial", request.getParameter("dataInicial"));
		model.addAttribute("dataFinal", request.getParameter("dataFinal"));
			
		
		if(sessaoUsuario != null) {
						
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("COMERCIAL")) {
							
				
				if(operacao!=null) {
					if(operacao.equals("Analise por Indústria")) {
						
						System.out.println(operacao);
						
						List<ColunasMesesBody> redeVendedorFaturamento = new ArrayList<>();
						redeVendedorFaturamento = new JdbcRedeVendedorIndustria().getRedeVendedorFaturamento(request.getParameter("dataInicial"), request.getParameter("dataFinal"));
						
						model.addAttribute("redeVendedorFaturamento", redeVendedorFaturamento);
						return "analiseRede";
					}
					
				
				}//FIM OPERACAO NULL
				
				return "analiseRede";
				
			}//FIM PERFIL
		
		}//FIM SESSAO
		
		return "index";
				
	}
	
	
	@RequestMapping("/editaObservacaoExpedicao")
	public String editaObservacaoExpedicao(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, ExpedicaoCarregamento expedicao) {
		System.out.println(expedicao.getNu_ped());
		
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		String texto="";
		String pedido = Integer.toString(expedicao.getNu_ped()); 
		
		if(sessaoUsuario != null) {
			model.addAttribute("pedido", expedicao.getNu_ped());
			System.out.println(request.getParameter("texto"));
			System.out.println(request.getParameter("operacao"));
			
			texto = new JdbcExpedicaoCarregamento().setObervacao(request.getParameter("texto"), pedido, request.getParameter("operacao"));
			System.out.println(texto);
			
			
			model.addAttribute("texto", texto);
			
			
			
			return "expedicao/observacao";
		}
		
		
		
		
		return "index";
	}
	
	
	@RequestMapping("/tracking")
	public String tracking(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		if(sessaoUsuario != null) {
			
			if(sessaoUsuario.getPerfil().equals("ADMINISTRADOR")||sessaoUsuario.getPerfil().equals("DIRETORIA")||sessaoUsuario.getPerfil().equals("COMERCIAL")||sessaoUsuario.getPerfil().equals("TRANSPORTE")) {
			
				
				
				Tracking pendEle = new Tracking();
				Tracking administracao = new Tracking();
				Tracking credito = new Tracking();
				Tracking roteirizacao = new Tracking();
				Tracking reserva = new Tracking();
				Tracking separacao = new Tracking();
				Tracking faturamento = new Tracking();
				Tracking expedicao = new Tracking();
				String mensagemExpedicao1="";
				
				System.err.println(request.getParameter("operacao"));
				
				
				if(request.getParameter("operacao")!=null) {
					if(request.getParameter("operacao").equals("op_expedicao")){
						expedicao = new JdbcTracking().trkExpedicao();
						
						if(expedicao.getQtdPedido()>0) {
							mensagemExpedicao1 = "PENDENTES: "+ expedicao.getQtdPedido()+" Pedidos - "+expedicao.getValorMoeda();
							
							List<TrackingExpedicao> filaExpedicao = new ArrayList<>();
							filaExpedicao = new JdbcTracking().detalheExpedicao();
							
							
							List<TrackingExpedicao> detalheExpedicao = new ArrayList<>();
							
							List<TrackingExpedicao> detalheAgendamento  = new ArrayList<>();
							
							List<TrackingExpedicao> pedidosAgendados = new ArrayList<>();
							pedidosAgendados = new JdbcTracking().agendamento("", "", "", "", "consultaAgendamentos");
							
																															
													
							if(pedidosAgendados.size()<=0) {
								
								model.addAttribute("detalheExpedicao", filaExpedicao);
								
								
							}else {
								for (TrackingExpedicao f:filaExpedicao) {
																	
									boolean agendado = false;
									
									for(TrackingExpedicao a:pedidosAgendados) {
										
																													
										if(a.getNuPed().equals(f.getNuPed())) {
											
											TrackingExpedicao registro = new TrackingExpedicao();
											
											registro.setNuPed(f.getNuPed());
											registro.setDtCadastro(f.getDtCadastro());
											registro.setCliente(f.getCliente());
											registro.setValorMoeda(f.getValorMoeda());
											registro.setNotaFiscal(f.getNotaFiscal());
											registro.setCarga(f.getCarga());
											registro.setAg_dt_Agedamento(" **PEDIDO AGENDADO PARA: "+Formata.data(a.getAg_dt_Agedamento()));
											
											detalheAgendamento.add(registro);
											
											agendado = true;

											break;
										} 
									
													
										
									}
									
									 if(!agendado) {
										
										
										TrackingExpedicao registro = new TrackingExpedicao();
										registro.setDtCadastro(f.getDtCadastro());
										registro.setNuPed(f.getNuPed());
										registro.setCliente(f.getCliente());
										registro.setValorMoeda(f.getValorMoeda());
										registro.setNotaFiscal(f.getNotaFiscal());
										registro.setCarga(f.getCarga());
										
										detalheExpedicao.add(registro);
									
								}
									
								}
								model.addAttribute("detalheExpedicao", detalheExpedicao);
								
							}

							
							
							
							model.addAttribute("mensagemExpedicao1", mensagemExpedicao1);
							model.addAttribute("pedidosAgendados", detalheAgendamento);
						}
					
						return "tracking/expedicao";
						
					}
					else {
						
						System.err.println("exec o else, e enviando o parametro"+request.getParameter("operacao"));
						List<PedidosDiario> detalhePedido = new ArrayList<>();
						
						detalhePedido = new JdbcTracking().detalhaPedidos(request.getParameter("operacao"));
						
						model.addAttribute("detalhePedido", detalhePedido);
						
						return "detalhePedidos";
						
					}
				}
				
				pendEle = new JdbcTracking().trkPendenciaEletronica();
				administracao = new JdbcTracking().trkAdministracao();
				credito = new JdbcTracking().trkCredito();
				roteirizacao = new JdbcTracking().trkRoteirizacao();
				reserva = new JdbcTracking().trkReserva();
				separacao = new JdbcTracking().trkSeparacao();
				faturamento = new JdbcTracking().trkFaturamento();
				expedicao = new JdbcTracking().trkExpedicao();
				
		
				
			
				model.addAttribute("administracao", administracao);
				model.addAttribute("credito", credito);
				model.addAttribute("roteirizacao", roteirizacao);
				model.addAttribute("reserva", reserva);
				model.addAttribute("separacao", separacao);
				model.addAttribute("faturamento", faturamento);
				model.addAttribute("expedicao", expedicao);
				model.addAttribute("pendEle", pendEle);
			
						
				return "tracking";
			}
			return "home";
		}
	
		return "index";
	}
	
	
	@RequestMapping("/agendaEntrega")
	public String agendaEntrega(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		
		String pedido = agendaEntrega.getNuPed();
		String texto=request.getParameter("texto");
		String data = request.getParameter("data");
		
		TrackingExpedicao registro = new TrackingExpedicao();
		List<TrackingExpedicao> agendamento = new ArrayList<>();
		
		
		
		if(request.getParameter("operacao")!=null) {
			if(request.getParameter("operacao").equals("gravar")) {
				
				pedido = request.getParameter("pedido");
				agendamento = new JdbcTracking().agendamento(request.getParameter("pedido"), request.getParameter("data"), sessaoUsuario.getNome(),request.getParameter("texto"),"insereRegistro");
				for(TrackingExpedicao a:agendamento) {
					texto=a.getAg_texto();
				}
				
			}else if(request.getParameter("operacao").equals("apagar")) {
				new JdbcTracking().agendamento(request.getParameter("pedido"), request.getParameter("data"), sessaoUsuario.getNome(), request.getParameter("texto"), request.getParameter("operacao"));
				
			}
			
			return "redirect:tracking";
			
			
		}else {
			System.out.println("EXECUTANDO O ELSE ");
			agendamento = new JdbcTracking().agendamento(agendaEntrega.getNuPed(), request.getParameter("data"), sessaoUsuario.getNome(),request.getParameter("texto"),"detalhaAgendamento");
			
			if(!agendamento.isEmpty()) {
				for(TrackingExpedicao a:agendamento) {
					texto=a.getAg_texto();
					data=a.getAg_dt_Agedamento();
					break;
				}
				
			}
		}
		
		System.out.println("texto: "+ texto);
		model.addAttribute("texto", texto);
		model.addAttribute("data", data);		
		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", agendaEntrega.getCliente());
		
		
		return "tracking/agendamento";
	}
	
	@RequestMapping("/romaneio")
	public String romaneio(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		Romaneio romaneio = new Romaneio();
		List<PedidosDiario> itemRomaneio = new ArrayList<>();
		
		romaneio = new JdbcTracking().romaneio(agendaEntrega.getCarga());
		itemRomaneio = new JdbcTracking().ItemRomaneio(agendaEntrega.getCarga());
		
		System.out.println(romaneio.getVeiculo());
		
		
		String pedido = agendaEntrega.getNuPed();
		String texto=request.getParameter("texto");
		String data = request.getParameter("data");
		
		
		model.addAttribute("romaneio", romaneio);
		model.addAttribute("itemRomaneio", itemRomaneio);
		
		return "tracking/romaneio";
	}
	
	
	@RequestMapping("/roteiros")
	public String roteiros(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		

		
	
		
		
		return "tracking/roteiros";
	}
	
	
	@RequestMapping("/despesas")
	public String analiseDespesa(Usuario usuario, HttpSession session, Model model, HttpServletRequest request) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
//		if(sessaoUsuario != null) {
//			
//					
//			
//			
//		}

				
		return "despesas/despesas";
	}
	
	
	
	@RequestMapping("/DespesaFornecedor")
	public String DespesaFornecedor(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		if(sessaoUsuario != null) {
			
			List<RelatorioDespesa> fornecedores = new ArrayList<>();
			
			fornecedores = new JdbcDespesas().getFornecedores();
			model.addAttribute("fornecedores", fornecedores);
			
		}

			
		
		return "despesas/config";
	}
	
	
	@RequestMapping("/confrontoEstoque")
	public String ConfrontoEstoque(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		if(sessaoUsuario != null) {
			
			List<ConfrontoEstoque> estoque = new ArrayList<>();
			
			boolean divergente = false;
			
			if(request.getParameter("divertente")!=null) {
								
				divergente=true;
			}
			
			estoque = new JdbcConfrontoEstoque().ConfrontoEstoque(divergente);
			
			System.out.println(request.getParameter("divertente"));
			
			model.addAttribute("estoque", estoque);
			
						
		}

			
		
		return "estoque/analise";
	}
	
	
	
	@RequestMapping("/detalhaPedidos")
	public String detalhaPedidos(Usuario usuario, HttpSession session, Model model, HttpServletRequest request, TrackingExpedicao agendaEntrega ) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("logado", sessaoUsuario);
		
		if(sessaoUsuario != null) {
			
			
			
						
		}

			
		
		return "estoque/analise";
	}
	
	
}//FIM CONTROLLER

