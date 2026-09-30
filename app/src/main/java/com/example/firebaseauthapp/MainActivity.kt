package com.example.firebaseauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauthapp.ui.theme.FirebaseauthappTheme
import com.google.firebase.auth.FirebaseAuth

private val Background = Color(0xFF08070D)
private val Surface = Color(0xFF14121A)
private val SurfaceLight = Color(0xFF1D1A25)
private val Gold = Color(0xFFD4A85A)
private val GoldLight = Color(0xFFF0D49A)
private val TextPrimary = Color(0xFFF5F0E8)
private val TextSecondary = Color(0xFFA9A19A)
private val ErrorRed = Color(0xFFFF687A)

data class PeriodoEscrita(
    val ano: String,
    val titulo: String,
    val local: String,
    val descricao: String,
    val detalhes: String,
    val curiosidade: String,
    val simbolo: String
)

private val periodos = listOf(
    PeriodoEscrita(
        ano = "c. 3200 a.C.",
        titulo = "Escrita Cuneiforme",
        local = "Mesopotâmia",
        descricao = "Uma das primeiras formas conhecidas de escrita.",
        detalhes = "A escrita cuneiforme surgiu na antiga Mesopotâmia. Os sinais eram pressionados em placas de argila usando um instrumento em formato de cunha. Inicialmente, era utilizada principalmente para registrar informações econômicas, administrativas e comerciais.",
        curiosidade = "O nome cuneiforme vem do latim e significa algo relacionado a 'forma de cunha'.",
        simbolo = "𒀭"
    ),
    PeriodoEscrita(
        ano = "c. 3100 a.C.",
        titulo = "Hieróglifos",
        local = "Egito Antigo",
        descricao = "Sistema de escrita formado por símbolos e representações.",
        detalhes = "Os hieróglifos egípcios combinavam símbolos que podiam representar objetos, ideias e sons. Eram utilizados em templos, monumentos, túmulos e documentos importantes.",
        curiosidade = "A escrita egípcia podia ser escrita em diferentes direções, dependendo da disposição dos símbolos.",
        simbolo = "𓂀"
    ),
    PeriodoEscrita(
        ano = "c. 800 a.C.",
        titulo = "Alfabeto Grego",
        local = "Grécia Antiga",
        descricao = "Um sistema alfabético que influenciou diversas escritas posteriores.",
        detalhes = "O alfabeto grego teve grande importância para o desenvolvimento da escrita ocidental. Ele ajudou a estabelecer um sistema no qual símbolos representavam principalmente sons da linguagem.",
        curiosidade = "As letras gregas deram origem a símbolos utilizados até hoje em matemática, ciência e tecnologia.",
        simbolo = "ΑΩ"
    ),
    PeriodoEscrita(
        ano = "c. 500–1500",
        titulo = "Manuscritos Medievais",
        local = "Europa Medieval",
        descricao = "Textos manuscritos produzidos e decorados por escribas.",
        detalhes = "Durante a Idade Média, muitos textos eram copiados manualmente em pergaminho. Alguns manuscritos recebiam letras ornamentadas, pinturas, folhas douradas e ilustrações conhecidas como iluminuras.",
        curiosidade = "A produção de alguns manuscritos podia levar meses ou até anos.",
        simbolo = "✒"
    ),
    PeriodoEscrita(
        ano = "c. 1450",
        titulo = "Tipografia",
        local = "Europa",
        descricao = "A impressão com tipos móveis transformou a produção de livros.",
        detalhes = "A prensa de tipos móveis associada a Johannes Gutenberg permitiu reproduzir textos em grande quantidade. A tecnologia contribuiu para a expansão da circulação de livros e informações na Europa.",
        curiosidade = "A Bíblia de Gutenberg é uma das obras mais famosas associadas ao início da impressão europeia com tipos móveis.",
        simbolo = "A"
    ),
    PeriodoEscrita(
        ano = "Séculos XX–XXI",
        titulo = "Era Digital",
        local = "Mundo",
        descricao = "A escrita passa dos materiais físicos para computadores e dispositivos digitais.",
        detalhes = "Computadores, celulares e a internet transformaram a maneira como escrevemos, publicamos e compartilhamos informações. As fontes tipográficas passaram a existir também como arquivos digitais.",
        curiosidade = "Hoje existem milhares de famílias tipográficas digitais utilizadas em sites, aplicativos, livros e sistemas operacionais.",
        simbolo = "</>"
    )
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirebaseauthappTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {

    val auth = FirebaseAuth.getInstance()

    var usuarioLogado by remember {
        mutableStateOf(auth.currentUser != null)
    }

    if (usuarioLogado) {

        TelaGaleria(
            onLogout = {
                auth.signOut()
                usuarioLogado = false
            }
        )

    } else {

        TelaLogin(
            onLoginSucesso = {
                usuarioLogado = true
            }
        )
    }
}

@Composable
fun FundoApp(
    content: @Composable () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0810),
                        Background,
                        Color(0xFF050509)
                    )
                )
            )
    ) {

        content()
    }
}

@Composable
fun TelaLogin(
    onLoginSucesso: () -> Unit
) {

    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    var mensagemErro by remember {
        mutableStateOf("")
    }

    var carregando by remember {
        mutableStateOf(false)
    }

    var telaCadastro by remember {
        mutableStateOf(false)
    }

    if (telaCadastro) {

        TelaCadastro(
            onCadastroSucesso = onLoginSucesso,
            onVoltarLogin = {
                telaCadastro = false
            }
        )

        return
    }

    FundoApp {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "MANUSCRITOS",
                color = Gold,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )

            Text(
                text = "& TIPOGRAFIA",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 3.sp
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "A evolução da escrita",
                color = TextSecondary,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(35.dp)
            )

            CardFuturista {

                Text(
                    text = "ACESSAR GALERIA",
                    color = GoldLight,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                CampoFuturista(
                    value = email,
                    onValueChange = {
                        email = it
                        mensagemErro = ""
                    },
                    label = "E-mail"
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                CampoFuturista(
                    value = senha,
                    onValueChange = {
                        senha = it
                        mensagemErro = ""
                    },
                    label = "Senha",
                    senha = true
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                BotaoFuturista(
                    texto = if (carregando) {
                        "ENTRANDO..."
                    } else {
                        "ENTRAR"
                    },
                    enabled = !carregando,
                    onClick = {

                        if (email.isBlank() || senha.isBlank()) {

                            mensagemErro =
                                "Preencha todos os campos."

                        } else {

                            carregando = true
                            mensagemErro = ""

                            auth.signInWithEmailAndPassword(
                                email,
                                senha
                            ).addOnCompleteListener { task ->

                                carregando = false

                                if (task.isSuccessful) {

                                    onLoginSucesso()

                                } else {

                                    mensagemErro =
                                        task.exception?.message
                                            ?: "Erro ao realizar login."
                                }
                            }
                        }
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextButton(
                    onClick = {
                        telaCadastro = true
                    }
                ) {

                    Text(
                        text = "Criar uma nova conta",
                        color = GoldLight
                    )
                }

                if (mensagemErro.isNotEmpty()) {

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = mensagemErro,
                        color = ErrorRed,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TelaCadastro(
    onCadastroSucesso: () -> Unit,
    onVoltarLogin: () -> Unit
) {

    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var mensagemErro by remember {
        mutableStateOf("")
    }

    var carregando by remember {
        mutableStateOf(false)
    }

    FundoApp {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "NOVA CONTA",
                color = Gold,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            CardFuturista {

                CampoFuturista(
                    value = email,
                    onValueChange = {
                        email = it
                        mensagemErro = ""
                    },
                    label = "E-mail"
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                CampoFuturista(
                    value = senha,
                    onValueChange = {
                        senha = it
                        mensagemErro = ""
                    },
                    label = "Senha",
                    senha = true
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                CampoFuturista(
                    value = confirmarSenha,
                    onValueChange = {
                        confirmarSenha = it
                        mensagemErro = ""
                    },
                    label = "Confirmar senha",
                    senha = true
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                BotaoFuturista(
                    texto = if (carregando) {
                        "CRIANDO..."
                    } else {
                        "CRIAR CONTA"
                    },
                    enabled = !carregando,
                    onClick = {

                        when {

                            email.isBlank() ||
                                    senha.isBlank() ||
                                    confirmarSenha.isBlank() -> {

                                mensagemErro =
                                    "Preencha todos os campos."
                            }

                            senha != confirmarSenha -> {

                                mensagemErro =
                                    "As senhas não são iguais."
                            }

                            senha.length < 6 -> {

                                mensagemErro =
                                    "A senha precisa ter pelo menos 6 caracteres."
                            }

                            else -> {

                                carregando = true
                                mensagemErro = ""

                                auth.createUserWithEmailAndPassword(
                                    email,
                                    senha
                                ).addOnCompleteListener { task ->

                                    carregando = false

                                    if (task.isSuccessful) {

                                        onCadastroSucesso()

                                    } else {

                                        mensagemErro =
                                            task.exception?.message
                                                ?: "Erro ao criar conta."
                                    }
                                }
                            }
                        }
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextButton(
                    onClick = onVoltarLogin
                ) {

                    Text(
                        text = "Já tenho uma conta",
                        color = GoldLight
                    )
                }

                if (mensagemErro.isNotEmpty()) {

                    Text(
                        text = mensagemErro,
                        color = ErrorRed,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TelaGaleria(
    onLogout: () -> Unit
) {

    var periodoSelecionado by remember {
        mutableStateOf<PeriodoEscrita?>(null)
    }

    if (periodoSelecionado != null) {

        TelaDetalhes(
            periodo = periodoSelecionado!!,
            onVoltar = {
                periodoSelecionado = null
            }
        )

        return
    }

    FundoApp {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 22.dp,
                        end = 22.dp,
                        top = 25.dp
                    )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "MANUSCRITOS",
                            color = Gold,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )

                        Text(
                            text = "& TIPOGRAFIA",
                            color = TextPrimary,
                            fontSize = 14.sp,
                            letterSpacing = 2.sp
                        )
                    }

                    TextButton(
                        onClick = onLogout
                    ) {

                        Text(
                            text = "SAIR",
                            color = GoldLight
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "GALERIA DA EVOLUÇÃO DA ESCRITA",
                    color = TextPrimary,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Da argila aos pixels.",
                    color = TextSecondary,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    start = 22.dp,
                    end = 22.dp,
                    bottom = 30.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                item {

                    TimelineHeader()
                }

                items(periodos) { periodo ->

                    CardPeriodo(
                        periodo = periodo,
                        onClick = {
                            periodoSelecionado = periodo
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TimelineHeader() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Surface)
            .border(
                1.dp,
                Gold.copy(alpha = 0.3f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(50))
                .background(Gold)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = "3200 a.C.",
            color = GoldLight,
            fontSize = 11.sp
        )

        Spacer(
            modifier = Modifier.width(6.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Gold.copy(alpha = 0.5f))
        )

        Spacer(
            modifier = Modifier.width(6.dp)
        )

        Text(
            text = "HOJE",
            color = GoldLight,
            fontSize = 11.sp
        )

        Spacer(
            modifier = Modifier.width(6.dp)
        )

        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(50))
                .background(Gold)
        )
    }
}

@Composable
fun CardPeriodo(
    periodo: PeriodoEscrita,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        SurfaceLight,
                        Surface
                    )
                )
            )
            .border(
                1.dp,
                Gold.copy(alpha = 0.25f),
                RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF24202A)),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = periodo.simbolo,
                color = GoldLight,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(15.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = periodo.ano,
                color = Gold,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = periodo.titulo,
                color = TextPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = periodo.local,
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = periodo.descricao,
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        Text(
            text = "›",
            color = Gold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun TelaDetalhes(
    periodo: PeriodoEscrita,
    onVoltar: () -> Unit
) {

    FundoApp {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 22.dp,
                end = 22.dp,
                top = 25.dp,
                bottom = 30.dp
            )
        ) {

            item {

                TextButton(
                    onClick = onVoltar
                ) {

                    Text(
                        text = "← VOLTAR À GALERIA",
                        color = GoldLight
                    )
                }

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF28222C),
                                    Color(0xFF111016)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            Gold.copy(alpha = 0.4f),
                            RoundedCornerShape(25.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = periodo.simbolo,
                        color = GoldLight,
                        fontSize = 70.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                Text(
                    text = periodo.ano,
                    color = Gold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = periodo.titulo,
                    color = TextPrimary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = periodo.local,
                    color = GoldLight,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                InfoBloco(
                    titulo = "SOBRE",
                    texto = periodo.detalhes
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                InfoBloco(
                    titulo = "CURIOSIDADE",
                    texto = periodo.curiosidade
                )

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                Text(
                    text = "LINHA DO TEMPO",
                    color = Gold,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                MiniTimeline()
            }
        }
    }
}

@Composable
fun InfoBloco(
    titulo: String,
    texto: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Surface)
            .border(
                1.dp,
                Gold.copy(alpha = 0.2f),
                RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {

        Text(
            text = titulo,
            color = Gold,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = texto,
            color = TextPrimary,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
    }
}

@Composable
fun MiniTimeline() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Surface)
            .padding(18.dp)
    ) {

        periodos.forEachIndexed { index, periodo ->

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Gold)
                    )

                    if (index < periodos.lastIndex) {

                        Spacer(
                            modifier = Modifier
                                .width(1.dp)
                                .height(30.dp)
                                .background(
                                    Gold.copy(alpha = 0.35f)
                                )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                Column {

                    Text(
                        text = periodo.ano,
                        color = Gold,
                        fontSize = 10.sp
                    )

                    Text(
                        text = periodo.titulo,
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun CampoFuturista(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    senha: Boolean = false
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        singleLine = true,
        visualTransformation = if (senha) {
            PasswordVisualTransformation()
        } else {
            androidx.compose.ui.text.input.VisualTransformation.None
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Gold,
            unfocusedBorderColor = Color(0xFF3A3540),
            focusedLabelColor = Gold,
            cursorColor = Gold,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        )
    )
}

@Composable
fun BotaoFuturista(
    texto: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Gold,
            contentColor = Color(0xFF17120B),
            disabledContainerColor = Gold.copy(alpha = 0.4f)
        )
    ) {

        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun CardFuturista(
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF191620),
                        Color(0xFF0E0D13)
                    )
                )
            )
            .border(
                1.dp,
                Gold.copy(alpha = 0.3f),
                RoundedCornerShape(25.dp)
            )
            .padding(22.dp)
    ) {

        content()
    }
}