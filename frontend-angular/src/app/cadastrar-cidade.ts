import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ImportsModule } from './imports';
import { Cidade } from '@domain/cidade';
import { ProjetoService } from '@service/projeto-service';
import { MessageService } from 'primeng/api';

//-------------------------------------------------------------------------------------
/** Tela para cadastro de cidades */
//-------------------------------------------------------------------------------------    
@Component({
    selector: 'cadastrar-cidade',
    templateUrl: 'cadastrar-cidade.html',
    standalone: true,
    imports: [ImportsModule],
    providers: [ProjetoService]
})
export class CadastrarCidade {

    //-------------------------------------------------------
    // Parâmetro de entrada para o componente
    //-------------------------------------------------------    
    @Input() public cidade: Cidade = new Cidade();   

    //-------------------------------------------------------
    // Evento lançado ao fechar a janela
    //-------------------------------------------------------    
    @Output('onClose') private eventoFechaJanela = new EventEmitter<boolean>();        

    //--------------------------------------------------------------
    /** Construtor. Recebe os services usados pelo componente */
    //--------------------------------------------------------------
    constructor(private service: ProjetoService, private messageService: MessageService) {}

    //-------------------------------------------------------------------------------------
    /** Método chamado ao clicar no botao 'salvar' */
    //-------------------------------------------------------------------------------------    
    public salvar() {
        // Salva a cidade no back end
        this.service.salvar(this.cidade).subscribe(retorno => {                        
            if(this.cidade.id) {
                this.messageService.add({ severity: 'success', summary: 'Info', detail: `Cidade '${this.cidade.nome}' alterada com sucesso!` });
            } else {
                this.messageService.add({ severity: 'success', summary: 'Info', detail: `Cidade '${this.cidade.nome}' cadastrada com sucesso!` });
            }
        },
        (error) => {
            if(error.status == 400){
                this.messageService.add({ severity: 'warn', summary: 'Info', detail: error.error.message });
            }
            console.log(error);
        });

        // Lança evento para fechar a janela
        this.eventoFechaJanela.emit(true) ;
    }

    //-------------------------------------------------------------------------------------
    /** Método chamado ao clicar no botao 'cancelar' */
    //-------------------------------------------------------------------------------------    
    public cancelar() {
        // Lança evento para fechar a janela
        this.eventoFechaJanela.emit(false) ;
    }

}