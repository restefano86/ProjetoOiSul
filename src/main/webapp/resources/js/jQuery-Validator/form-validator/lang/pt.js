/**
 * JQUERY-FORM-VALIDATOR
 * 
 * @website by
 * @license MIT
 * @version 2.2.83
 */
!function(a, b) {
	"use strict";
	a(b)
			.bind(
					"validatorsLoaded",
					function() {
						a.formUtils.LANG = {
							errorTitle : "O formul&#225;rio n&#227;o pode ser enviado!",
							requiredFields : "Campo obrigat&#243;rio n&#227;o preenchido",
							badTime : "A hora digitada n&#227;o &#233; v&#225;lida",
							badEmail : "O e-mail digitado n&#227;o &#233; v&#225;lido",
							badTelephone : "O telefone digitado n&#227;o &#233; v&#225;lido",
							badSecurityAnswer : "A pergunta de segurança não foi respondida corretamente",
							badDate : "A data digitada não é válida",
							lengthBadStart : "Sua resposta deve incluir entre ",
							lengthBadEnd : " caracteres",
							lengthTooLongStart : "O limite desse campo &#233; ",
							lengthTooShortStart : "Sua resposta deve ter pelo menos ",
							notConfirmed : "As informações digitadas não puderam ser confirmadas",
							badDomain : "O dom&#237;nio digitado n&#227;o &#233; v&#225;lido",
							badUrl : "A URL digitada não é válida",
							badCustomVal : "Os dados digitados n&#227;o s&#227;o v&#225;lidos",
							andSpaces : "&#160;e espa&#231;os",
							badInt : "O n&#250;mero digitado n&#227;o &#233; v&#225;lido",
							badSecurityNumber : "O número de seguro social digitado não é válido",
							badUKVatAnswer : "O número do VAT digitado não é válido para o Reino Unido",
							badStrength : "Senha muito fraca",
							badNumberOfSelectedOptionsStart : "Selecione pelo menos",
							badNumberOfSelectedOptionsEnd : " alternativa(s)",
							badAlphaNumeric : "Use somente caracteres alfanuméricos (letras a-z e números)",
							badAlphaNumericExtra : " e",
							wrongFileSize : "O arquivo selecionado é maior que o tamanho máximo permitido (%s)",
							wrongFileType : "Somente arquivos %s são permitidos",
							groupCheckedRangeStart : "Por favor, escolha entre ",
							groupCheckedTooFewStart : "Por favor, escolha pelo menos ",
							groupCheckedTooManyStart : "Por favor, escolhe no máximo ",
							groupCheckedEnd : " alternativa(s)",
							badCreditCard : "O número de cartão de crédito digitado não é válido",
							badCVV : "O código de segurança do cartão de crédito não é válido",
							wrongFileDim : "As dimensões da imagem não são válidas",
							imageTooTall : "a imagem não pode ser mais alta que ",
							imageTooWide : "a imagem não pode ser mais larga que ",
							imageTooSmall : "a imagem é muito pequena",
							min : "min",
							max : "max",
							imageRatioNotAccepted : "A proporção da imagem (largura x altura) não é válida",
							badBrazilTelephoneAnswer : "O número de telefone digitado é inválido",
							badBrazilCEPAnswer : "O CEP digitado é inválido",
							badBrazilCPFAnswer : "O CPF digitado é inválido"
						}
					})
}(jQuery, window);