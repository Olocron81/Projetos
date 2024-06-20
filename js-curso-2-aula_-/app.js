// Esta comentado essas quatro linhas de código, pois estamos melhorando a performance do código.
// manipulando html por JavaScript 
//let titulo = document.querySelector('h1');
// Botando um texto no H1
//titulo.innerHTML = 'Jogo de Adivinhar';

//let paragrafo = document.querySelector('p');
//paragrafo.innerHTML = 'Escolha um número entre 1 e 10: ';

// Função
//function popBoasVindas() {
//    alert('Boas vindas ao jogo!');
//}
//Chamando a função 
//popBoasVindas();

let listaDeNumerosSorteados = [];
let numeroLimite = 10;
let tentativas = 1; 
// função com parametro
function exibirTextoNaTela(tag, texto){
    // Pegando um campo do Html  
    let campo = document.querySelector(tag);
    // texto é para diferenciar h1 e p, pois ambos são do mesmo tipo. 
    campo.innerHTML = texto;
    responsiveVoice.speak(texto, 'Brazilian Portuguese Female', {rate:1.2});
}

exibirTextoNaTela('h1', 'Jogo de Adivinhar');
exibirTextoNaTela('p', 'Escolha um número entre 1 e 10: ');

// Função 
let numeroSecreto = gerarNumeroAleatorio();
function gerarNumeroAleatorio() {
    // o valor será retornado para a varivel numeroSecreto 
    let numeroEscolhido = parseInt(Math.random() * numeroLimite + 1);
    let quantidadeDeElementosNaLista = listaDeNumerosSorteados.length;
    // Limpando a lista se tiver utilizado todos os elementos dela
    if (quantidadeDeElementosNaLista == 10) {
        listaDeNumerosSorteados = [];
    }
    // Verifica se o numero foi incluido, se sim, gera um novo numero.
    if (listaDeNumerosSorteados.includes(numeroEscolhido)) {
        return gerarNumeroAleatorio();
    } 
    // Adicionando número a lista
    else {
        listaDeNumerosSorteados.push(numeroEscolhido);
        return numeroEscolhido;
    }
}


// Função sem parametro
function verificarChute() {
    let chute = document.querySelector('input').value;
    
    if (chute == numeroSecreto) {
        exibirTextoNaTela('h1', `PARABÉNS`);
        let palavraTentativa = tentativas == 1 ? 'tentativa' : 'tentativas';
        let mensagemTentativas = `O numero secreto é ${numeroSecreto} e foram ${tentativas} ${palavraTentativa} para vencer!`;
        exibirTextoNaTela('p', mensagemTentativas);

        document.getElementById('reiniciar').removeAttribute('disabled')
    } else {
        if (chute > numeroSecreto){
            exibirTextoNaTela('p', `O numero é MENOR.`);
        }
        else {
            exibirTextoNaTela('p', `O numero é MAIOR.`);
        }
     tentativas++  
     limparCampo(); 
    }
}

function limparCampo() {
        chute = document.querySelector('input');
        chute.value = '';
}

function reiniciarJogo() {
    numeroSecreto = gerarNumeroAleatorio();
    limparCampo();
    tentativas = 1;
    exibirMensagemInicial();
    document.getElementById('reiniciar').setAttribute('disabled', true)
}

function exibirMensagemInicial() {
    exibirTextoNaTela('h1', 'Jogo de Adivinhar');
    exibirTextoNaTela('p', 'Escolha um número entre 1 e 10: ');
}












