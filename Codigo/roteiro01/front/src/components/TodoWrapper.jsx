import React from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import './TodoWrapper.css';
export const TodoWrapper = () => {
    return (
        <div className='TodoWrapper'>
            <h1>Lista de Tarefas</h1>
            <TodoForm />
            <TodoList />
        </div>
        
    );
}
