import { Link, useNavigate } from "react-router-dom";
import "./home.css"
import { useState } from "react";
import { useTheme } from "../context/ThemeContext";

export default function Home(){

    const navigate = useNavigate();

    const toRegister = () => {
        navigate("/register")
    };

    const { theme, toggleTheme } = useTheme();
      const [menuOpen, setMenuOpen] = useState(false);
    
      const handleThemeChange = (selected: "light" | "dark") => {
        if (selected !== theme) toggleTheme();
        setMenuOpen(false);
      };

    return(
        <div className="conteneur-home">
            <header className="header-home">
                <div className="app_name">
                    <h1 className="h1-home">GalaxyTasks</h1>
                </div>
                <div className="nav">
                    <a className="a-home" href="#">Solution</a>
                    <a className="a-home" href="#">A propos</a>
                    <a className="a-home" href="#">FAQ</a>
                    <Link className="my-account"to="/login">Mon compte</Link>
                </div>
                <div className="switcher">
                        <button className="themeButton" onClick={() => setMenuOpen(!menuOpen)}>
                            Theme
                        </button>
                        
                        {menuOpen && (
                            <div className="opened">
                            <button className="lightButton"
                                onClick={() => handleThemeChange("light")}>
                                Clair
                            </button>
                            <button className="darkButton"
                                onClick={() => handleThemeChange("dark")}>
                                Sombre
                            </button>
                            </div>
                        )}
                </div>
            </header>
            <section className="section-home">
                <div className="comment">
                    <h1 className="h1-home">
                        La <span className="highlight_platform"> meilleure plateforme</span>
                        de gestion de tâches collaborative
                    </h1>
                    <p className="p-home">
                        Découvrez GalaxyTasks : la plateforme collaborative et moderne qui
                        vous permet de gérer et structurer vos tâches de manière efficiente.
                    </p>
                    <div className="options">
                        <button className="register-login" onClick={toRegister}> Nous rejoindre </button>
                        <a className="a-home" href=".section-home-2"> En savoir plus </a>
                    </div>
                </div>
                <aside className="aside-register-presentation"></aside>
            </section>
        </div>
    );
}