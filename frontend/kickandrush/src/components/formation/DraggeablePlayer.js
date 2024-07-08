import { useDrag } from 'react-dnd'
import  ItemTypes  from './ItemTypes'

const style = {
  position: 'absolute',
  borderRadius: '50%',
  backgroundColor: 'white',
  padding: '0.5rem 1rem',
  cursor: 'move',
  zIndex:1,
  width: '25px', // 원하는 크기로 설정
  height: '25px', // 원하는 크기로 설정
}

const DraggeablePlayer = ({ id, playerLeft, playerTop,  playerName ,playerNumber}) => {
  const [{ isDragging }, drag] = useDrag(
    () => ({
      type: ItemTypes.BOX,
      item: { id, left: playerLeft, top: playerTop, playerName,playerNumber },
      collect: (monitor) => ({
        isDragging: monitor.isDragging(),
      }),
    }),
    [id, playerLeft, playerTop]
  )
  if (isDragging ) {
    return <div ref={drag} />
  }
  return (
    <div
      ref={drag}
      style={{ ...style, left: playerLeft,top: playerTop}}
    >
      {id}
    </div>
  )
}

export default DraggeablePlayer