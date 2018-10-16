package com.giomerito.vsis;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.giomerito.vsis.domain.enums.EstadoPagamento;
import com.giomerito.vsis.domain.enums.TipoCliente;
import com.giomerito.vsis.repositories.CategoriaRepository;
import com.giomerito.vsis.repositories.CidadeRepository;
import com.giomerito.vsis.repositories.ClienteRepository;
import com.giomerito.vsis.repositories.EnderecoRepository;
import com.giomerito.vsis.repositories.EstadoRepository;
import com.giomerito.vsis.repositories.ItemPedidoRepository;
import com.giomerito.vsis.repositories.PagamentoRepository;
import com.giomerito.vsis.repositories.PedidoRepository;
import com.giomerito.vsis.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaVsisApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaVsisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Sorvete");
		Categoria cat2 = new Categoria(null, "Picolé");
		
		Produto p1 = new Produto(null, "Sorvete 200ml", 1.50);
		Produto p2 = new Produto(null, "Picolé cremoso", 0.75);
		Produto p3 = new Produto(null, "Picolé fruta", 0.75);
		
		
		//Relacionado as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		
		//Relacionando os produtos com as Categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));			
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
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
		
		estadoRepository.saveAll(Arrays.asList(e1,e2,e3));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
		
		Cliente cli1 = new Cliente(null, "Giomerito Alves de Souza", "giomerito.dev@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(77) 99966-6600", "(62) 99869-2532"));
		Cliente cli2 = new Cliente(null, "Giullia Alves dos Santos", "giomerito.souza@gmail.com", "36378912388", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("(77) 99919-7398", "(62) 99869-2532"));
		
		
		Endereco end1 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 2", "Jardim das Acácias", "47.850-000", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 2", "Jardim das Acácias", "47.850-000", cli2, c2);
		Endereco end3 = new Endereco(null, "Rua do Oitiseiro", "1586", "Casa 1", "Jardim das Acácias", "47.850-000", cli2, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2, end3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComDinheiro(null, EstadoPagamento.QUITADO, ped2, 120.00);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		//Pedidos identifica seus itens
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		//O Produto identifica os itens
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
