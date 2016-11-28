# virtual-host

---

## Início

Para adquirir o projeto, siga os passos:

* 1. Obtenha o projeto do github

```
git clone git@github.com:danielpcampagna/virtual-host.git
```

* 2. Instale as dependências, compile e inicie o serviço

```
make install // baixa e instala as dependências
make setup   // compila o projeto com as dependências
make start   // inicia o projeto com as depenências e as configurações do arquivo `conf/default.json`
```


## Para colaborar com o projeto, siga os passos:

* 1. Obtenha a versão atual estável (`master`)

```
git checkout master
git pull origin master
```

* 2. Crie uma nova `branch` a partir da master

```
git checkout -b feature/nome-da-sua-branch
```

* 3. Após as alterações, o commit e o push

```
git add .  // Adicione apenas os arquivos desejados
git commit -m "mensagem-do-commit"
git push origin feature/nome-da-sua-branch
```

* 4. Atualize a master e faça o merge dela para a sua branch

```
git checkout master
git pull origin master
git checkout feature/nome-da-sua-branch
git merge master
```

* 4. Caso esteja funcionando, faça o merge do seu conteúdo para a master

```
git checkout master
git pull origin master
git merge feature/nome-da-sua-branch
```
