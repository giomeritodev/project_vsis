package com.giomerito.vsis.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.giomerito.vsis.domain.Categoria;
import com.giomerito.vsis.domain.Cidade;
import com.giomerito.vsis.domain.Cliente;
import com.giomerito.vsis.domain.Endereco;
import com.giomerito.vsis.domain.Estado;
import com.giomerito.vsis.domain.ItemPedido;
import com.giomerito.vsis.domain.Pagamento;
import com.giomerito.vsis.domain.PagamentoComCartao;
import com.giomerito.vsis.domain.PagamentoComDinheiro;
import com.giomerito.vsis.domain.Pedido;
import com.giomerito.vsis.domain.Produto;
import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.domain.enums.EstadoPagamento;
import com.giomerito.vsis.domain.enums.TipoCliente;
import com.giomerito.vsis.domain.enums.TipoUsuario;
import com.giomerito.vsis.repositories.CategoriaRepository;
import com.giomerito.vsis.repositories.CidadeRepository;
import com.giomerito.vsis.repositories.ClienteRepository;
import com.giomerito.vsis.repositories.EnderecoRepository;
import com.giomerito.vsis.repositories.EstadoRepository;
import com.giomerito.vsis.repositories.ItemPedidoRepository;
import com.giomerito.vsis.repositories.PagamentoRepository;
import com.giomerito.vsis.repositories.PedidoRepository;
import com.giomerito.vsis.repositories.ProdutoRepository;
import com.giomerito.vsis.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instantiateTestDatabase() throws ParseException {

		Usuario usu = new Usuario(null, "Giomerito Alves de Souza", "giomerito", pe.encode("giogiu"), TipoUsuario.ADMIN);

		usuarioRepository.saveAll(Arrays.asList(usu));

		Categoria cat1 = new Categoria(null, "Imformatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, 10, "0100000000000", "Computador", 2000.00);
		Produto p2 = new Produto(null, 20, "0200000000000", "Impressora", 800.00);
		Produto p3 = new Produto(null, 30, "0300000000000", "Mouse", 80.00);
		Produto p4 = new Produto(null, 40, "0400000000000", "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, 50, "0500000000000", "Toalha", 50.00);
		Produto p6 = new Produto(null, 60, "0600000000000", "Colcha", 200.00);
		Produto p7 = new Produto(null, 70, "0700000000000", "TV true color", 1200.00);
		Produto p8 = new Produto(null, 80, "0800000000000", "Roçadeira", 800.00);
		Produto p9 = new Produto(null, 90, "0900000000000", "Abajour", 100.00);
		Produto p10 = new Produto(null, 100, "1000000000000", "Pendente", 100.00);
		Produto p11 = new Produto(null, 110, "1100000000000", "Shampoo", 90.00);

		// Relacionado as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		// Relacionando os produtos com as Categorias
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado e1 = new Estado(null, "Bahia", "BA");
		Estado e2 = new Estado(null, "Goias", "GO");
		Estado e3 = new Estado(null, "São Paulo", "SP");

		Cidade c1 = new Cidade(null, "Luis Eduardo Magalhães", e1);
		Cidade c2 = new Cidade(null, "Goiânia", e2);
		Cidade c3 = new Cidade(null, "São Paulo", e3);
		Cidade c4 = new Cidade(null, "Barreiras", e1);
		Cidade c5 = new Cidade(null, "Irecê", e1);

		e1.getCidades().addAll(Arrays.asList(c1, c4, c5));
		e2.getCidades().addAll(Arrays.asList(c2));
		e3.getCidades().addAll(Arrays.asList(c3));

		estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

		Cliente cli1 = new Cliente(null, "Giomerito Alves de Souza", "giomerito.dev@gmail.com", "36378912377",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(77) 99966-6600", "(62) 99869-2532"));
		Cliente cli2 = new Cliente(null, "Giullia Alves dos Santos", "giomerito.souza@gmail.com", "36378912388",
				TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("(77) 99919-7398", "(62) 99869-2532"));

		Endereco end1 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 2", "Jardim das Acácias", "47.850-000",
				cli1, c1);
		Endereco end2 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 2", "Jardim das Acácias", "47.850-000",
				cli2, c2);
		Endereco end3 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 1", "Jardim das Acácias", "47.850-000",
				cli2, c1);

		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2, end3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComDinheiro(null, EstadoPagamento.QUITADO, ped2, 700.00, 0.00);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		// Pedidos identifica seus itens
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		// O Produto identifica os itens
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
