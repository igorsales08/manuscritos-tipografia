# 📜 Manuscritos & Tipografia

> **Uma viagem pela evolução da escrita — das tabuletas cuneiformes aos pixels da era digital.**

<p align="center">
  <strong>📜 Manuscritos & Tipografia</strong><br>
  <em>Galeria interativa sobre a história e evolução da escrita</em>
</p>

---

## 🏛️ Sobre o projeto

**Manuscritos & Tipografia** é um aplicativo Android desenvolvido para apresentar, de forma interativa e visual, a evolução da escrita ao longo da história.

O projeto acompanha diferentes momentos da humanidade, começando com os primeiros sistemas de escrita conhecidos e chegando à era digital.

A proposta é transformar um conteúdo histórico em uma experiência de exploração, permitindo que o usuário navegue por uma **linha do tempo**, selecione diferentes períodos e consulte informações sobre cada etapa da evolução da escrita.

---

## 🕰️ Linha do tempo

O aplicativo apresenta seis momentos principais:

| Período           | Tema                      | Local           |
| ----------------- | ------------------------- | --------------- |
| 🪨 c. 3200 a.C.   | **Escrita Cuneiforme**    | Mesopotâmia     |
| 🏺 c. 3100 a.C.   | **Hieróglifos**           | Egito Antigo    |
| 🏛️ c. 800 a.C.   | **Alfabeto Grego**        | Grécia Antiga   |
| ✒️ c. 500–1500    | **Manuscritos Medievais** | Europa Medieval |
| 🖨️ c. 1450       | **Tipografia**            | Europa          |
| 💻 Séculos XX–XXI | **Era Digital**           | Mundo           |

---

## ✨ Funcionalidades

### 🔐 Autenticação

O aplicativo possui sistema de autenticação utilizando **Firebase Authentication**.

O usuário pode:

* Criar uma conta;
* Fazer login com e-mail e senha;
* Permanecer autenticado;
* Sair da conta.

### 📚 Galeria histórica

Após realizar o login, o usuário acessa a galeria principal com os diferentes períodos da evolução da escrita.

Cada período possui um card interativo contendo:

* Data aproximada;
* Nome do período;
* Local de origem;
* Descrição;
* Representação visual.

### 🔎 Tela de detalhes

Ao selecionar um período, o aplicativo apresenta uma página específica com:

* Informações históricas;
* Contexto do período;
* Curiosidades;
* Representação visual;
* Linha do tempo completa.

### 🧭 Navegação

O usuário pode navegar entre:

```text
LOGIN
  ↓
GALERIA
  ↓
PERÍODO HISTÓRICO
  ↓
DETALHES
  ↓
VOLTAR À GALERIA
```

---

## 🎨 Design

O aplicativo utiliza uma identidade visual inspirada na combinação entre **história e tecnologia**.

### Características visuais

* 🌑 Fundo escuro;
* 🟡 Detalhes dourados;
* 📜 Referências visuais a manuscritos;
* ✨ Cards com bordas e gradientes;
* 🔤 Tipografia destacada;
* 📱 Interface adaptada para dispositivos Android.

A ideia visual é representar a transição:

**ARGILA → PERGAMINHO → PAPEL → IMPRESSÃO → TELA DIGITAL**

---

## 🛠️ Tecnologias utilizadas

O projeto foi desenvolvido utilizando:

### Android

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **Android Studio**

### Backend / Autenticação

* **Firebase Authentication**

### Interface

* Compose UI
* Layouts responsivos
* Gradientes
* Cards interativos
* Timeline
* Navegação entre telas

---

## 🧩 Estrutura do aplicativo

A estrutura principal do fluxo é:

```text
                    ┌───────────────┐
                    │    LOGIN      │
                    └───────┬───────┘
                            │
                    Firebase Auth
                            │
                            ▼
                  ┌───────────────────┐
                  │      GALERIA      │
                  └─────────┬─────────┘
                            │
             ┌──────────────┼──────────────┐
             │              │              │
             ▼              ▼              ▼
        Cuneiforme      Hieróglifos      Grego
             │              │              │
             └──────────────┼──────────────┘
                            │
                            ▼
                  Manuscritos Medievais
                            │
                            ▼
                       Tipografia
                            │
                            ▼
                       Era Digital
```

---

## 📱 Telas

### 🔐 Login

Tela inicial do aplicativo responsável pela autenticação do usuário através do Firebase.

### 📝 Cadastro

Permite que novos usuários criem uma conta utilizando e-mail e senha.

### 🏛️ Galeria

Apresenta a linha do tempo e os diferentes períodos históricos.

### 📖 Detalhes

Apresenta informações aprofundadas sobre o período selecionado.

---

## 🚀 Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/manuscritos-tipografia.git
```

### 2. Abra no Android Studio

Abra a pasta do projeto no **Android Studio**.

### 3. Configure o Firebase

O projeto utiliza o Firebase Authentication.

Para configurar o Firebase:

1. Crie um projeto no Firebase;
2. Adicione um aplicativo Android;
3. Utilize o mesmo package name do projeto;
4. Configure o Firebase Authentication;
5. Ative o método de login por **E-mail/Senha**;
6. Adicione o arquivo de configuração do Firebase ao projeto.

### 4. Sincronize o Gradle

Aguarde o Android Studio baixar as dependências necessárias.

### 5. Execute

Conecte um dispositivo Android ou utilize um emulador e execute o projeto.

---

## 🔐 Segurança

O projeto utiliza o **Firebase Authentication** para gerenciamento de usuários.

Arquivos contendo credenciais privadas ou informações sensíveis **não devem ser adicionados ao repositório**.

Antes de realizar um `git push`, verifique sempre os arquivos que serão enviados:

```bash
git status
```

---

## 🎯 Objetivo acadêmico

Este projeto foi desenvolvido como uma aplicação prática envolvendo:

* Desenvolvimento Android;
* Programação em Kotlin;
* Construção de interfaces com Jetpack Compose;
* Autenticação utilizando Firebase;
* Organização de informações históricas;
* Desenvolvimento de interfaces interativas;
* Experiência do usuário.

O tema escolhido foi **"Manuscritos e Tipografia: Galeria da evolução da escrita, de tabuletas cuneiformes a manuscritos medievais iluminados"**.

---

## 📚 Conteúdo apresentado

O aplicativo aborda a transformação dos meios de registro e comunicação humana, mostrando como a escrita passou por diferentes tecnologias e formatos ao longo dos séculos.

A trajetória apresentada no aplicativo é:

> **Cuneiforme → Hieróglifos → Alfabeto → Manuscritos → Tipografia → Era Digital**

Essa evolução demonstra como a necessidade humana de registrar, preservar e compartilhar informações acompanhou o desenvolvimento das sociedades e das tecnologias.

---

## 👨‍💻 Desenvolvimento

Projeto desenvolvido por:

**Igor Sales Moreira**

Curso: **Desenvolvimento de Sistemas**

Projeto acadêmico desenvolvido utilizando **Kotlin, Jetpack Compose e Firebase**.

---

## 📌 Status

🟢 **Em desenvolvimento**

O projeto pode receber novas funcionalidades, melhorias visuais, imagens históricas, animações e novos períodos da evolução da escrita.

---

<p align="center">
  <strong>📜 Do barro ao código.</strong><br>
  <em>A história da escrita também é a história da tecnologia.</em>
</p>
