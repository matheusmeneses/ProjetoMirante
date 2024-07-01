import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cidade } from '@domain/cidade';
import { Observable, from, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
    
@Injectable()
export class ProjetoService {

    constructor(private http: HttpClient) {}

    url = 'http://localhost:8080/mirante/cidades';

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    //------------------------------------------------
    /** Recupera a lista de cidades */
    //------------------------------------------------
    pesquisarCidades(): Observable<any> {
        // TODO: chamar backend, ao invés de retornar uma lista fake
        return this.http.get<Cidade[]>(this.url)
        .pipe(
             retry(2),
             catchError(this.handleError))
    }    

    //------------------------------------------------
    /** Exclui a cidade informada */
    //------------------------------------------------
    excluir(cidade: Cidade): Observable<any> {        
        // TODO: chamar backend
        return this.http.delete<Cidade[]>(this.url + '/' + cidade.id, this.httpOptions)
        .pipe(
            retry(1),
            catchError(this.handleError));
    }

    //------------------------------------------------
    /** Salva a cidade informada */
    //------------------------------------------------    
    salvar(cidade: Cidade): Observable<any> {
        // TODO: chamar backend
        return this.http.post<Cidade>(this.url, JSON.stringify(cidade), this.httpOptions)
        .pipe(
            retry(2),
            catchError(this.handleError));
    }

    // Manipulação de erros
   handleError(error: HttpErrorResponse) {
        return throwError(error);
    }

};