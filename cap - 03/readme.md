# Capítulo 3 - Implementação das entidades
A implementação das entidades é simples. Cada uma das entidades representam
uma tabela, que possui só duas colunas (id e nome/titulo).

Você pode obter mais detalhes sobre _persistence_ [aqui](https://jakarta.ee/zh/specifications/persistence/3.1/jakarta-persistence-spec-3.1.pdf).

### @Entity
O modulo ___spring boot JPA___ adiciona ao projeto um pacote chamado _jakarta_, que disponibiliza
várias ferramentas para nos auxiliar no controle dos dados persistentes, que são os dados que serão 
salvos no banco de dados.

A anotação ___Entity___ diz ao Java que a classe representa uma tabela no banco de dados. Além disso,
a anotação ___entity___ define alguns pre-réquisitos, que são:
- A classe deve ter um construtor sem argumentos.
- Deve ser uma classe. Uma entidade não pode ser uma interface ou um enum.
- Não pode ser final. A classe em si, nem seus métodos ou argumentos podem ser finais.


### @Table, @Column e @Id
Cada uma dessas anotações servem para definir de forma explicita a tabela, coluna e id de uma entidade.
Caso a classe, e os argumentos possuam nomes iguais da tabela, as anotações ___@Table___ e ___@Column___
podem ser ignoradas, mas ___@Id___ ainda deve ser usada.


### @GeneratedValue
Esta anotação é usada para definir que um argumento da classe tem seus valores gerados automaticamente.
Você pode passar para ela a estratégia que é seguida para gerar esses valores.

As estratégias são:

- AUTO
- SEQUENCE
- IDENTITY
- UUID


## Implementando as entidades
Você pode usar a estrutura que você quiser. A estrutura que vou usar não é um padrão e nem muito menos obrigatória.

Eu gosto de criar, para cada entidade, uma pasta onde será agregado todos os códigos relacionados a entidade.
Para o nosso caso, vamos tratar a tabela da relação como uma entidade também.

Então, crie três pastas:
- Book
- Client
- ClientBooks

Dentro de cada uma dessas pastas crie um arquivo com uma classe com o mesmo nome da pasta.
Cada classe deve ser anotada com ___@entity___.

Para a classe __Client__ defina os argumentos:
- private Integer id
- private String nome

E para __Book__ defina:
- private Integer id
- private String title

Coloque as anotações ___@Id___ e ___@GeneratedValue___ nos ids.
Para a anotação _GeneratedValue_ passe o atributo: __strategy = GenerationType.IDENTITY__.

A classe _ClientBook_ vai ser um pouco especial, pois ela representa uma relação, e por isso
não tem uma chave primária, e sim uma chave composta.
Para isso, o JPA oferta duas maneira:
- EmbeddableId
- ClassId

O motivo de termos que criar uma chave primária é que o JPA exige que uma entidade tenha
uma chave primária.

Vou utilizar _ClassId_ por ser mais simples, mas EmbeddableId funciona da mesma forma.

Para utilizar _ClassId_ devemos criar uma classe que irá representar a chave primária. Os argumentos dessa classe deve
ter os mesmos nomes que os argumentos, que representam a chave composta, têm. No nosso caso, a chave composta tem 2 partes:
"client_id" e "book_id".

Crie uma classe, dentro da pasta __ClientBook__, com o nome ClientBooksId. Essa classe deve ter como parâmetros:
- private Client ___client___
- private Book ___book___

Essa classe deve implementar a interface Serializable.

Usamos a anotação ___@Data___, do pacote _lombok_, na classe para evitar escrever códigos para:
- Getters
- Setters
- toString
- Equal
- HashCode

Não se esquece de implementar o construtor que receba todos os parâmetros.

Depois disso, na classe ClientBooks coloque a notação @IdClass, e passe para ela como parâmetro a classe anterior.

## Implementando a relação
A relação entre cliente e livro é de 1 para N, pois 1 cliente pode alugar vários livros ao mesmo tempo,
mas um livro não pode ser alugado várias vezes simultaneamente. Por isso usamos a tabela _ClientBooks_ para representar essa relação.

O JPA possui anotações que auxiliam na implementação, que são:
- @OneToOne
- @OneToMany
- @ManyToOne
- @ManyToMany

Existem outras anotações, mas nós só vamos usar _@oneToMany_ e @ManyToOne

Na classe cliente vamos criar um atributo ___List<ClientBooks>___ de nome books, e anotado com @OneToMany.
A anotação vai receber os seguintes parâmetros:
> mappedBy = "client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY

- mappedBy faz referência a um atributo na classe ClientBooks, que vamos criar.

Na classe books vamos criar um atributo ___ClientBooks___ de nome client, e anotado com @OneToOne.
A anotação vai receber os seguintes parâmetros:
> mappedBy = "book", cascade = CascadeType.MERGE, fetch = FetchType.LAZY

Na classe __ClientBooks__ vamos adicionar ao atributo __client__ a anotação
> @ManyToOne @JoinColumn(name = "client_id", referencedColumnName = "id")

e para o atributo __book__ adicionamos:
> @OneToOne @JoinColumn(name = "book_id", referencedColumnName = "id")

