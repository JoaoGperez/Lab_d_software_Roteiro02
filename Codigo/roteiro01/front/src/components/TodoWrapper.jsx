import React, { useState, useEffect } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import { getTodos, createTodo, updateTodo, deleteTodo } from '../api';
import './TodoWrapper.css';

export const TodoWrapper = () => {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        const response = await getTodos();
        setTodos(response.data);
    };

    const addTodo = async (description, dueDate, priority) => {
        const newTodo = {
            description,
            dueDate,
            priority,
            completed: false,
        };
        await createTodo(newTodo);
        fetchTodos();
    };

    const handleDeleteTodo = async (id) => {
        await deleteTodo(id);
        fetchTodos();
    };

    const handleToggleComplete = async (id) => {
        const todo = todos.find((todo) => todo.id === id);
        await updateTodo(id, { ...todo, completed: !todo.completed });
        fetchTodos();
    };

    const handleEditTodo = async (id, updatedTodo) => {
        await updateTodo(id, updatedTodo);
        fetchTodos();
    };

    return (
        <div className='TodoWrapper'>
            <h1>Lista de Tarefas</h1>
            <TodoForm addTodo={addTodo} />
            {todos.map((item) => (
                <TodoList
                    key={item.id}
                    task={item}
                    deleteTodo={handleDeleteTodo}
                    editTodo={handleEditTodo}
                    toggleComplete={handleToggleComplete}
                />
            ))}
        </div>
    );
};
