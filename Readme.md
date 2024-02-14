Annotations used :

**@Entity** : Used to identify an a class as an entity in a database.

**@Id** : used to identify the primary key of a table or an entity.

**@Generated Value** : Generates a value based on the strategy it is given 

**@GeneratedValue(strategy = GenerationType.IDENTITY)** : This is used to generate a value based on the auto increment
function in database

**@Column** : Allows to rename a column as given in the column annotation
ex : @Column(name = "author_id")

**@OneToMany(mappedBy = "author" ,orphanRemoval = true,cascade = CascadeType.ALL)** : Notes a one to many relationship
with another table. mapped by function provides a foreign key reference to the other table.
orphanRemoval function provides access to delete any child rows if the parent is deleted. cascade = cascadeType.ALL 
provides cascading functions to delete parent rows along with it's children.

**@OnDelete(action = onDeleteAction.CASCADE)** : Provides basically the on delete cascade funtion in mysql but forces 
hibernate to provide the same result as Hibernate JPA does not have delete cascade.

**@ManyToOne** : Notes a Many to one relationship with another table supposedly with another one to many relationship.

**@JoinColumn(name = "auth_id",referencedColumnName = "author_id")** : Joins a coulmn to the given table based on the 
reference it is given by the "referencedColumnName".

HQL Answers 
1. Hibernate: create table Author (author_id integer not null auto_increment, country varchar(255), name varchar(255), primary key (author_id)) engine=InnoDB
Hibernate: create table Book (id integer not null auto_increment, price float(53), publicationYear integer, title varchar(255), auth_id integer, primary key (id)) engine=InnoDB
Hibernate: alter table Book add constraint FK1rfcbo3xardkmdfmg7m46pwen foreign key (auth_id) references Author (author_id) on delete cascade
Hibernate: insert into Author (country,name) values (?,?)
Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
Hibernate: insert into Author (country,name) values (?,?)
Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
Hibernate: select b1_0.id,b1_0.auth_id,b1_0.price,b1_0.publicationYear,b1_0.title from Book b1_0 where b1_0.publicationYear>?
3   |   Americanah   |   Chimamanda Ngozi Adichie    |   2812.5    |   2013  |


2. Hibernate: create table Author (author_id integer not null auto_increment, country varchar(255), name varchar(255), primary key (author_id)) engine=InnoDB
   Hibernate: create table Book (id integer not null auto_increment, price float(53), publicationYear integer, title varchar(255), auth_id integer, primary key (id)) engine=InnoDB
   Hibernate: alter table Book add constraint FK1rfcbo3xardkmdfmg7m46pwen foreign key (auth_id) references Author (author_id) on delete cascade
   Hibernate: insert into Author (country,name) values (?,?)
   Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
   Hibernate: insert into Author (country,name) values (?,?)
   Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
   Hibernate: insert into Book (auth_id,price,publicationYear,title) values (?,?,?,?)
   Enter Author id ; 1
   Hibernate: update Book set price=(price+((price/100)*10)) where auth_id=?
   Updated Rows : 1
   Hibernate: select b1_0.id,b1_0.auth_id,b1_0.price,b1_0.publicationYear,b1_0.title from Book b1_0
   1   |   Sherlock Holmes   |   Arthur Conan Doyle    |   400.0    |   1996  |    
   2   |   Half of a Yellow Sun   |   Chimamanda Ngozi Adichie    |   2699.25    |   2006  |    
   3   |   Americanah   |   Chimamanda Ngozi Adichie    |   2812.5    |   2013  |

3. Hibernate: delete from Author where author_id=?
   Authors deleted :1
   Hibernate: select a1_0.author_id,a1_0.country,a1_0.name from Author a1_0
   2   |   Chimamanda Ngozi Adichie
4. Hibernate: select avg(b1_0.price) from Book b1_0
   [1970.5833333333333]
5. Hibernate: select a1_0.name,count(b1_0.id) from Author a1_0 left join Book b1_0 on a1_0.author_id=b1_0.auth_id group by a1_0.author_id
   Arthur Conan Doyle:  Count  1
   Chimamanda Ngozi Adichie:  Count  2

6. Enter a country : Nigeria
   Hibernate: select b1_0.id,b1_0.auth_id,b1_0.price,b1_0.publicationYear,b1_0.title from Book b1_0 join Author a1_0 on a1_0.author_id=b1_0.auth_id where a1_0.country=?
   Hibernate: select a1_0.author_id,a1_0.country,a1_0.name from Author a1_0 where a1_0.author_id=?
   | 2| Half of a Yellow Sun| 2006| 2699.25
   | 3| Americanah| 2013| 2812.5
7. 
10. Hibernate: select a1_0.author_id,a1_0.country,a1_0.name from Author a1_0 join Book b1_0 on a1_0.author_id=b1_0.auth_id group by a1_0.author_id having count(b1_0.id)>(select avg(derived1_0.bookCount) from (select count(b2_0.id) from Book b2_0 group by b2_0.auth_id) derived1_0(bookCount))
   Chimamanda Ngozi Adichie