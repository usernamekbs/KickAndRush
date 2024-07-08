import React, { useState, useEffect,useCallback } from 'react';
import Button from '@mui/material/Button';
import { Link, useParams } from 'react-router-dom';
import { useDrag, DndProvider, useDrop } from 'react-dnd';
import "../../css/Formation.css";
import ItemType from './ItemTypes';
import DraggeablePlayer from './DraggeablePlayer'
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";

const CreateFormation = () => {
    const accessToken = localStorage.getItem('accessToken');
    const category = useParams();
    const [headCoach, setHeadCoach] = useState();
    const [teamName, setTeamName] = useState();

    const handleTeamNameChange = (e) => {
        setTeamName(e.currentTarget.value)  
    }

    const handleHeadCoachChange = (e) => {
        setHeadCoach(e.currentTarget.value)
    }

    const initialPlayers = [
        { id: 1, playerName: 'Player 1',playerNumber: 9, playerTop: 20, playerLeft: 225 },
        { id: 2, playerName: 'Player 2',playerNumber: 2, playerTop: 35, playerLeft: 50 },
        { id: 3, playerName: 'Player 3',playerNumber: 5, playerTop: 50, playerLeft: 20 },
        { id: 4, playerName: 'Player 4',playerNumber: 22, playerTop: 50, playerLeft: 40 },
        { id: 5, playerName: 'Player 5',playerNumber: 23, playerTop: 50, playerLeft: 60 },
        { id: 6, playerName: 'Player 6',playerNumber: 20, playerTop: 50, playerLeft: 80 },
        { id: 7, playerName: 'Player 7',playerNumber: 99, playerTop: 70, playerLeft: 20 },
        { id: 8, playerName: 'Player 8',playerNumber: 15, playerTop: 70, playerLeft: 40 },
        { id: 9, playerName: 'Player 9',playerNumber: 12, playerTop: 70, playerLeft: 60 },
        { id: 10, playerName: 'Player 10',playerNumber: 9, playerTop: 70, playerLeft: 80 },
        { id: 11, playerName: 'Player 11',playerNumber: 54, playerTop: 90, playerLeft: 50 },
    ];

    const [players, setPlayers] = useState(initialPlayers);

    const moveBox = useCallback(async (id, playerLeft, playerTop,playerName,playerNumber) => {
        setPlayers((prevPlayers) =>
            prevPlayers.map((player) =>
                player.id === id ? { ...player, playerName,playerNumber,playerTop, playerLeft } : player
            )
        );
       

    }, []);

    const createPlayers = async() =>{
        const formationData = {
            teamName: teamName,
            headCoach: headCoach,
            players: players,
        };
    
        try {
            const result = await axios.post(`http://localhost:8080/api/formation/create`,formationData);
        } catch (error) {
            console.log('Error updating player position:', error);
        }
    }

    const [, drop] = useDrop(
        () => ({
            accept: ItemType.BOX,
            drop(item, monitor) {
                const delta = monitor.getDifferenceFromInitialOffset();
                const left = Math.round(item.left + delta.x);
                const top = Math.round(item.top + delta.y);
                moveBox(item.id, left, top);
                return undefined;
            },
        }),
    );

    return (
        <>
            <h2>해당 게시판은 포메이션 대결 메뉴입니다</h2>
            <TextField
                fullWidth
                label="팀 이름을 입력해주세요"
                type="text"
                variant="standard"
                onChange={handleTeamNameChange} 
                />
            <TextField
                fullWidth
                label="감독을 입력해주세요"
                type="text"
                variant="standard"
                onChange={handleHeadCoachChange} 
                />
            <div className='filed' ref={drop}>
                <div className='field-container'>
                    {players.map((player) => {
                        const { id,playerLeft, playerTop, playerName,playerNumber } = player
                        return (
                            <DraggeablePlayer
                                id={id}
                                playerLeft={playerLeft}
                                playerTop={playerTop}
                                playerName={playerName}
                                playerNumber={playerNumber}
                            >
                            </DraggeablePlayer> 
                        )
                    })}
                </div>
            </div>
            {/* {accessToken && ( */}
                <Link to={`/category/${category.categoryId}/formation/create`} style={{ textDecoration: "none", color: 'white' }}>
                    <Button variant="contained" color="primary" onClick={createPlayers}>저장</Button>
                </Link>
            {/* )} */}
        </>
    );
};

export default CreateFormation;