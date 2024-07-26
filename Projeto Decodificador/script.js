// Função para descriptografar o texto
function descriptografarTexto(texto) {
    const chaves = {
        'enter': 'e',
        'imes': 'i',
        'ai': 'a',
        'ober': 'o',
        'ufat': 'u'
    };
    return texto.replace(/enter|imes|ai|ober|ufat/g, match => chaves[match]);
}

// Função para criptografar o texto
function criptografarTexto(texto) {
    const chaves = {
        'e': 'enter',
        'i': 'imes',
        'a': 'ai',
        'o': 'ober',
        'u': 'ufat'
    };
    return texto.replace(/[eiaou]/g, match => chaves[match.toLowerCase()] || match);
}

// Função para armazenar o texto do textarea e exibir a versão criptografada ou descriptografada
function guardarTexto(acao) {
    const texto = document.querySelector('.Text_Area').value;
    let textoProcessado;

    if (acao === 'criptografar') {
        textoProcessado = criptografarTexto(texto);
    } else if (acao === 'descriptografar') {
        textoProcessado = descriptografarTexto(texto);
    }

    // Atualiza a seção direita com o texto processado
    const campoDireito = document.querySelector('.Campo_Direito');
    campoDireito.innerHTML = `<div class="conteudo">
                                <h2>${acao === 'criptografar' ? 'Texto Criptografado' : 'Texto Descriptografado'}</h2>
                                <p>${textoProcessado}</p>
                              </div>`;

    // Adiciona ou remove a classe para ocultar a imagem
    campoDireito.classList.add('ocultar-imagem');
}

// Função para exibir a imagem quando não tem texto
function exibirImagem() {
  const campoDireito = document.querySelector('.Campo_Direito');
  campoDireito.classList.remove('ocultar-imagem'); // Garante que a classe que oculta a imagem seja removida
  campoDireito.innerHTML = `
    <div class="conteudo">
      <img src="imagens/image2.png" alt="Imagem de uma pessoa com lupa" class="Imagem_Direita">
      <h2>Nenhuma mensagem encontrada</h2>
      <p>Digite um texto que gostaria de criptografar ou descriptografar.</p>
    </div>`;
}

// Função para copiar o texto processado
function copiarTexto() {
    const campoDireito = document.querySelector('.Campo_Direito p');
    if (campoDireito) {
        const texto = campoDireito.textContent;
        navigator.clipboard.writeText(texto).then(() => {
            alert('Texto copiado para a área de transferência!');
        }).catch(err => {
            alert('Erro ao copiar o texto: ', err);
        });
    }
}

// Adiciona eventos aos botões
document.querySelector('.Criptografar').addEventListener('click', () => guardarTexto('criptografar'));
document.querySelector('.Descriptografar').addEventListener('click', () => guardarTexto('descriptografar'));
document.querySelector('.Copiar').addEventListener('click', copiarTexto);

// Adiciona um evento para exibir a imagem quando a página carrega ou está vazia
document.addEventListener('DOMContentLoaded', exibirImagem);
