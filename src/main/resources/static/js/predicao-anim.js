// Animação de barra de progresso e loading para predição IA
function animatePredictionBar(targetPercent) {
    const bar = document.getElementById('pred-bar');
    const label = document.getElementById('pred-bar-label');
    let current = 0;
    const duration = 1200; // ms
    const step = Math.max(1, Math.round(targetPercent / (duration / 16)));
    bar.style.width = '0%';
    label.innerText = '0%';
    bar.setAttribute('aria-valuenow', 0);
    bar.classList.add('progress-animated');
    function animate() {
        if (current < targetPercent) {
            current += step;
            if (current > targetPercent) current = targetPercent;
            bar.style.width = current + '%';
            label.innerText = current + '%';
            bar.setAttribute('aria-valuenow', current);
            requestAnimationFrame(animate);
        } else {
            bar.style.width = targetPercent + '%';
            label.innerText = targetPercent + '%';
            bar.setAttribute('aria-valuenow', targetPercent);
            bar.classList.remove('progress-animated');
        }
    }
    animate();
}

function showPredictionLoading() {
    const loading = document.getElementById('pred-loading');
    if (loading) loading.style.display = 'block';
}
function hidePredictionLoading() {
    const loading = document.getElementById('pred-loading');
    if (loading) loading.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function() {
    const percent = parseInt(document.getElementById('pred-bar').getAttribute('data-pred-final'));
    if (!isNaN(percent)) {
        showPredictionLoading();
        setTimeout(function() {
            hidePredictionLoading();
            animatePredictionBar(percent);
        }, 600);
    }
});
