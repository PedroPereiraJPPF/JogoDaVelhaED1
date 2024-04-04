package com.velha.arquivo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.velha.collections.linkedList.singlyLinkedList.SinglyLinkedList;

public class ArchiveManager<T> {
        // Métodos de salvar e ler a lista
        public void salvarEmBinario(SinglyLinkedList<T> lista, String nomeArquivo) throws IOException {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
                outputStream.writeObject(lista);
            }
        }
        
        @SuppressWarnings("unchecked")
        public SinglyLinkedList<T> lerBinario(String nomeArquivo) throws IOException, ClassNotFoundException {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
                return (SinglyLinkedList<T>) inputStream.readObject();
            }
        }
}
