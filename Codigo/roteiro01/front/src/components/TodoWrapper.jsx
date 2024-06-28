import React, { useState, useEffect } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import { getTodos, createTodo, updateTodo, deleteTodo } from './todoService';
import './TodoWrapper.css';

export const TodoWrapper = () => {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await getTodos();
            // Verifique se a resposta contém um objeto `content` e use-o
            const todosData = response.data.content ? response.data.content : response.data;
            setTodos(todosData);
        } catch (error) {
            console.error('Error fetching tasks:', error);
            setTodos([]);
        }
    };

    const addTodo = async (description, dueDate, priority) => {
        const newTodo = {
            description,
            dueDate,
            priority,
            completed: false,
        };
        try {
            await createTodo(newTodo);
            fetchTodos();
        } catch (error) {
            console.error('Error adding todo:', error);
        }
    };

    const handleDeleteTodo = async (id) => {
        try {
            await deleteTodo(id);
            fetchTodos();
        } catch (error) {
            console.error('Error deleting todo:', error);
        }
    };

    const handleToggleComplete = async (id) => {
        const todo = todos.find((todo) => todo.id === id);
        try {
            await updateTodo(id, { ...todo, completed: !todo.completed });
            fetchTodos();
        } catch (error) {
            console.error('Error toggling complete:', error);
        }
    };

    const handleEditTodo = async (id, updatedTodo) => {
        try {
            await updateTodo(id, updatedTodo);
            fetchTodos();
        } catch (error) {
            console.error('Error editing todo:', error);
        }
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
