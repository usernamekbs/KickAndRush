import React from 'react'; 
import '../css/Layout.css'
import { Outlet } from 'react-router-dom';

const Main = () => {
    
    return (
        <>
            <main className="mainList"> 
                <section>
                    <div>
                        <Outlet/>
                    </div> 
                </section>
            </main>
        </>
    );

}
export default Main;